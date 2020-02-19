'''
    Write a python server program that
        0. initialized a socket connection on localhost and port 10000
        1. accepts a connection from a  client
        2. receives a "Hi <name>" message from the client
        3. generates a random numbers and keeps it a secret
        4. sends a message "READY" to the client
        5. waits for the client to send a guess
        6. checks if the number is
            6.1 equal to the secret then it should send a message "Correct! <name> took X attempts to guess the secret"
            6.2 send a message "HIGH" if the guess is greater than the secret
            6.3 send a message "LOW" if the guess is lower than the secrent
        7. closes the client connection and waits for the next one
'''
import socket
from random import randint

server_socket = socket.socket(socket.AF_INET,socket.SOCK_STREAM);
server_socket.bind(('localhost',10000))
server_socket.listen(1)
# while True:
# 	connection, clietn_address = server_socket.accept()
# 	print("Connetion established!")
# 	while True:
# 		client_data = connection.recv(1024)
# 		if (client_data != "q" and len(client_data) > 0):
# 			print(client_data.decode("utf-8"))
# 			connection.send(client_data)
# 		else:
# 			print(client_data.decode("utf-8"))
# 			connection.send(client_data)
# 			break
# 	if (client_data != 'q'):
# 		connection.close()


while True:
	connection, client_address = server_socket.accept()
	count = 0;
	name = connection.recv(1024).decode("utf-8")
	print(name[3:])
	value = randint(0,100)
	print("vaue = ",value)
	connection.send("READY".encode("utf-8"))
	while True:
		client_data = int(connection.recv(1024).decode("utf-8"))
		count += 1
		if client_data == value:
			connection.send(str(f"Correct! {name} took {count} attempts to guess the secret").encode("utf-8"))
			break
		elif value > client_data:
			connection.send("LOW".encode("utf-8"))
		else:
			connection.send("HIGH".encode("utf-8"))
	connection.close()