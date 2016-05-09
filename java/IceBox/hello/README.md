To run this demo, open two terminal windows. In the first window,
start the IceBox server:

#java -classpath build/classes/main:/usr/local/share/java/icebox-3.6.2.jar:/usr/local/share/java/ice-3.6.2.jar IceBox.Server --Ice.Config=config.icebox
$ java IceBox.Server --Ice.Config=config.icebox

In the second window, run the client:

#java -cp build/classes/main:$CLASSPATH Client
$ java -jar build/libs/client.jar

To shut down IceBox, use IceBox.Admin:

#java IceBox.Admin --Ice.Config=../../../config.admin shutdown
$ java IceBox.Admin --Ice.Config=config.admin shutdown
