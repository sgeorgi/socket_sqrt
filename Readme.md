# SocketSqrt

A simple socket server that returns the square root of a given integer. It uses the Akka actor system to create a socket
server and handles incoming connections in a separate socket handler. It should be scalable to the point your JVM or 
 computer melts down...

This is a learning project, so it will probably not be the best Scala you've seen. And yes, having a socket to communicate
with to get the rounded square sums of random integers is probably not a kick-ass application either ;-)

## Example usage

Open up a terminal and execute `sbt`. The SBT will download all necessary libraries and Scala itself, type in `reStart` to
start the socket server, use `reStop` to stop.

Open another terminal and connect to the server with `telnet localhost 8000`. You can now type in arbitrary integers
and get the square root back. Type in 'exit' to exit the session.

Run ``sbt test`` in a terminal or `test` inside a running SBT session to run the test/s

## Next up

 * Refine SqrtSpec to actually use some mocking (framework is already included, see build.sbt)
 * Write Specs for both SocketServer and SocketHandler that simulate and verify the passing of messages between the actors
 * <s>Use an "implicit" to convert Strings to ByteStrings automatically</s>
 * <s>Use an "implicit" to convert ByteStrings to String automatically</s>
 * <s>Write input parser to extract an Int if possible, and make the SocketHandler use it</s>
 
