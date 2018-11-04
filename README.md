[![][ButlerImage]][website] 


A simple RPC framework base on Netty.
==========
## 1. Overview ##
-----
	
RenRPC is an easy-to-use RPC framework, that base on netty.io ,zookeeper, protobuf,etc.
The server-side is built upon netty which supports asynchronous and non-blocking io functionality, while the client-side provides a wide variety of options to communicate with server, which includes short live connection, keep-alive tcp connection, high availability and failover strategy.



## 2. Features ##
-----
- Simple and small code base, low cost to learn and use.
- With high performance[see benchmark data]
- Support sync and async call for your various requirement.
- Long lived persistent connection, reconnect to server automatically with heartbeat check.
- Service register and discovery use zookeeper.
- load balance with round robin and consistent hash,etc.
- protobuf encoder/decoder.
- With spring DI and parameter configuration.
- Other language support(unimplemented yet).


## 3. Usage: ##
-----
**1. Define an obj interface**
	
	public interface Rng {
		int nextInt(int max);
		long nextLong(long max);
	}
**2. Implements the previous defined interface**
	@RpcService(Rng.class)
	public class RngImpl implements Rng {
		private UniformRandomProvider rng ;
		private static RngImpl INSTANCE = new RngImpl();
	
		private RngImpl() {
			// Instantiate a "Mersenne-Twister" generator with a factory method.
			rng = RandomSource.create(RandomSource.MT);
		}
		public static RngImpl getInstance() {
			return INSTANCE;
		}
	
		@Override
		public int nextInt(int max) {
			return rng.nextInt(max);
		}
	
		@Override
		public long nextLong(long max) {
			return rng.nextLong(max);
		}
	}



**3. **Complete 2 configuration files, one is zookeeper location and spring bean scan in server-side.****
	
	// zookeeper address configuration	
	rng.registry.address=192.168.56.101:2181

	// beans configuration in spring
	<context:component-scan base-package="com.hy.rng.service.impl" />

	<context:property-placeholder location="classpath:rng-service-config.properties" />

	<!-- configuration for service registry component -->
	<bean id="serviceRegistry" class="com.hy.ren.rpc.registry.zk.ZKServiceRegistry">
		<constructor-arg name="zkAddress" value="${rng.registry.address}" />
	</bean>

	<!-- configuration for RPC service -->
	<bean id="rpcProvider" class="com.hy.ren.rpc.provider.RpcProvider">
		<constructor-arg name="serviceAddress" value="${rng.service.address}" />
		<constructor-arg name="serviceRegistry" ref="serviceRegistry" />
	</bean>
	

**4. Start server with simplest way**

	new ClassPathXmlApplicationContext("spring-rng-service.xml");

**5. Complete 2 configuration files, one is zookeeper location and spring bean scan in client-side.**

	please refer to server-side configuration.

**6. Complete client invocation code according your need.**

	ApplicationContext context = new ClassPathXmlApplicationContext("spring-rng-client.xml");
	RpcProxy rpcProxy = context.getBean(RpcProxy.class);

	Rng rng = null;
	try {
		rng = rpcProxy.create(Rng.class, "");
	} catch (InterruptedException e) {
		logger.error(e.getMessage());
	}

All is done for your RPC call. 


## 4. Performance test ##
-----
 [in progress]



[ButlerImage]: https://github.com/weixuan2008/RenRPC/blob/master/renrpc-master/RenRPC.png
[website]: https://github.com/weixuan2008/RenRPC