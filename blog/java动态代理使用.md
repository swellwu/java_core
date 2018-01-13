# java动态代理 com.aop.proxy
#静态代理
## 说明
静态代理模式是GOF23种设计模式之一，运用非常广泛。uml如下：
![image_1ba0rursfbqf877mi51tp0jo313.png-10.5kB][1]
Proxy-代理类
RealSubject-真实类
Subject-代理类和真实类的接口
Client-客户
真正的实现是在RealSubject中，但是给客户调用的是Proxy类方法，而代理类调用真实类中的相同的方法。
这样做的目的就是保证了真实类的最大复用程度，不同的客户可能有些许定制的需要，可以通过不同的代理类来提供操作。
## 示例
一个小例子：
接口：
```
public interface ISubject {
    void request();
}
```
真实类：
```
public class RealSubject implements ISubject{

    @Override
    public void request() {
        System.out.println("realSubject requesting");
    }
}
```
代理类：
```
public class ProxySubject implements ISubject {
    private ISubject realSubject;
    public ProxySubject() {
        realSubject = new RealSubject();
    }

    @Override
    public void request() {
        System.out.println("do something before");
        realSubject.request();
        System.out.println("do something after");
    }
}
```
可以看出，代理类的核心就是在调用真实类的方法前后加入一些定制操作，这里就是打log。
使用：
```
public class Test {
    public static void main(String[] args) {
        RealSubject realSubject = new RealSubject();
        ISubject subject = (ISubject)SubjectInvocationHandler.getProxyInstanceFactory(realSubject);
        subject.request();
    }
}
```
输出：
```
    before
    realSubject requesting
    after
```
# 动态代理
## 说明
动态代理的最大特定就是这个代理类不需要我们来定义，是在程序自动生成的，我们只需要定义代理类和真实类相比，增强操作的部分。静态模式中是在代理类中的方法中调用真实类的相同方法完成的。
### jdk动态代理
java为我们提供了增强规则的接口：InvocationHandler,而动态生成类由Proxy类来完成。
InvocationHandler
```
说明：
InvocationHandlerInvocationHandler is the interface implemented by the invocation handler of a proxy instance.
Each proxy instance has an associated invocation handler. When a method is invoked on a proxy instance, the method invocation is encoded and dispatched to the invoke method of its invocation handler.

每一个动态代理类都关联一个InvocationHandler实现类（handler），当调用代理类的一个方法时，会转发到此handler的invoke方法中。这个invoke方法就是需要我们来实现的。

主要接口：
Object invoke(Object proxy, Method method, Object[] args)
proxy:　 代理对象
method:　调用的方法对象
args:　　方法的参数

由于此接口有：代理对象、方法、方法参数三个对象，所以可以将所有的代理关系交由一个类来实现。
```
Proxy类：
```
主要接口：
public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h) throws IllegalArgumentException

loader:     加载动态代理类的ClassLoader
interfaces: 指定动态代理的接口，因为所有的代理关系可以交由一个handler处理，所有需要指定具体接口才能实现多态
h:　　      handler
```
基本使用示例：
```
public class JdkDynamicProxy implements InvocationHandler {
    private Object obj;

    public JdkDynamicProxy(Object obj) {
        this.obj = obj;
    }

    public static Object getProxyInstance(Object realObj) {
        Class<?> classType = realObj.getClass();
        return Proxy.newProxyInstance(classType.getClassLoader(),
                classType.getInterfaces(), new JdkDynamicProxy(realObj));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        System.out.println("before");
        method.invoke(obj, args);
        System.out.println("after");
        return null;
    }
}
```
### cglib动态代理
使用字节码技术，通过继承目标类的方式生成子类实现代理。
基本使用示例：
```
public class CglibDynamicProxy implements MethodInterceptor {

    private Object target;

    public Object getProxyInstance(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object target, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("before");
        Object result = methodProxy.invokeSuper(target, args);
        System.out.println("after");
        return result;
    }
}
```
# 动态代理优点
1. 代理模式就是为了提高真实方法的复用度，而用不同代理来实现不同的需求，兼顾了复用和灵活度。
2. 动态代理相对于静态代理，不需要手动写代理类，只需要编写调用处理器handler，自动生成代理类，提高了自动化程度。
# jdk动态代理和cglib动态代理的区别
1. jdk动态代理是针对接口的，所以只能对实现了接口的类生成动态代理，而cglib不需要。
2. cglib是采用继承的方式，所以final修饰的方法无法代理。
3. cglib采用字节码技术，在运行期动态生成class，效率较高，jdk动态代理采用反射技术，效率较差。

  [1]: http://static.zybuluo.com/swellwu/ta85undu1rrfy9ynu40f79e7/image_1ba0rursfbqf877mi51tp0jo313.png