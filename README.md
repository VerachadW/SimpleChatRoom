# SimpleChatRoom
Simple chat android client and server. The application is written in Kotlin using [Java-WebSocket](http://java-websocket.org/).
For sever, it is written in Python with [Tornado](http://www.tornadoweb.org/en/stable/).

## Server Setup
1. open Terminal and go to ```chatroom-server``` directory
2. run ```pip install -r requirements.txt```, then ```python app.py```

## Client Setup
1. Open ```ChatRoomFragment``` file in ```chatroom-android``` directory
2. Change ```SERVER_URL``` to your server address e.g. ```ws://x.x.x.x:8888```
3. Run the app and see the result.
