import socket

client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
client_socket.connect((socket.gethostname(), 8000))
while True:
	msg = input("Enter a sentence: ")
	client_socket.send(msg.encode("utf-8"))
	print(client_socket.recv(1024).decode("utf-8"))
	if(msg == "q"):
		break