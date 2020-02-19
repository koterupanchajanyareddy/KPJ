import socket

server_socket = socket.socket(socket.AF_INET,socket.SOCK_STREAM);
server_socket.bind((socket.gethostname(),8000))
server_socket.listen(1)
while True:
	connection, clietn_address = server_socket.accept()
	# try:
	# 	while True:
	# 		client_data = connection.revc(1024)
	# 		if client_data:
	# 			print(client_data.decode('utf-8'))
	# 			connection.sendall(client_data)
	# 		else:
	# 			break
	# finally:
	# 	server_socket.close()
	print("Connetion established!")
	while True:
		client_data = connection.recv(1024)
		if (client_data != "q" and len(client_data) > 0):
			print(client_data.decode("utf-8"))
			connection.send(client_data)
		else:
			print(client_data.decode("utf-8"))
			connection.send(client_data)
			break
	if (client_data != 'q'):
		connection.close()

	# try:
	# 	while True:
	# 		client_data = connection.revc(1024)
