'''
    Write a python client program that
        0. connects to localhost and port 10000
        1. send a "Hi <name>" message
        2. waits for the server to send the "READY" message
        3. guess a number and send to the server
        4. wait for the server to send the message
        5. Read the message and make a decision based on the following
            4.1 Close the client if the message is of the form "Correct! <name> took X attempts to guess the secret"
            4.2 Use the clue given by the server and repeat from step 3
'''
import socket
from random import randint

client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
client_socket.connect(('localhost', 10000))
# while True:
# 	msg = input("Enter a sentence: ")
# 	client_socket.send(msg.encode("utf-8"))
# 	print(client_socket.recv(1024).decode("utf-8"))
# 	if(msg == "q"):
# 		break

    
while True:
	client_socket.send("Hi PJ".encode("utf-8"))
	msg = client_socket.recv(1024).decode("utf-8")
	if(msg == "READY"):
		while True:
			rand = input("Guess: ")
			client_socket.send(rand.encode("utf-8"))
			st = client_socket.recv(1024).decode("utf-8")
			if st == "LOW":
				print("Guessed is less than the value")
				continue
			elif st == "HIGH":
				print("guessed is greater than the value")
				continue
			else :
				print(st)
				break
		break


