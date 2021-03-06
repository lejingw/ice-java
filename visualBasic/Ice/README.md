Demos in this directory:

- [async](./async)

  This demo illustrates the use of Asynchronous Message Invocation
  (AMI) and Asynchronous Message Dispatch (AMD).

- [bidir](./bidir)

  This demo shows how to use bidirectional connections for callbacks.
  This is typically used if the server cannot open a connection to the
  client to send callbacks, for example, because firewalls block
  incoming connections to the client.

- [callback](./callback)

  A simple callback demo that illustrates how a client can pass a
  proxy to a server, invoke an operation in the server, and the server
  call back into an object provided by the client as part of that
  invocation.

- [hello](./hello)

  This demo illustrates how to invoke ordinary (twoway) operations, as
  well as how to invoke oneway operations, use datagrams, secure
  invocations, and how to use batched invocations.

- [invoke](./invoke)

  This demo illustrates the use of the Ice streaming API.

- [latency](./latency)

  A simple latency test that measures the basic call dispatch delay of
  Ice.

- [minimal](./minimal)

  This demo illustrates a minimal Ice application.

- [nested](./nested)

  A demo to illustrate how nested callbacks work, and how the size of
  the thread pool affects the maximum nesting depth.

- [session](./session)

  This demo shows how to use sessions to clean up client-specific
  resources in a server after the client shuts down or crashes.

- [throughput](./throughput)

  A simple throughput demo that allows you to send sequences of
  various types between client and server and to measure the maximum
  bandwidth that can be achieved using serialized synchronous
  requests.

- [value](./value)

  This demo shows how to use classes, class factories, and the
  difference between local and remote invocations of class operations.
