from tornado import websocket, web, ioloop
import json

channels = []

class ChatSocketHandler(websocket.WebSocketHandler):
	def check_origin(self, origin):
		return True

	def open(self):
		if (self not in channels):
			channels.append(self)

	def on_message(self, message):
		for c in channels:
			c.write_message(message)

	def on_close(self):
		if (self in channels):
			channels.remove(self)


app = web.Application([
	(r'/', ChatSocketHandler)
	])


if __name__ == '__main__':
	app.listen(8888)
	ioloop.IOLoop.instance().start()