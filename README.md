# P2P File Transfer like Torrent
## 1. Introduction
This project is a simple implementation of a P2P file transfer system like torrent. The implementation is by Java and JavaFx. You can see the system design here:
![System Design](https://github.com/nhoxtin15/CNass1/blob/main/Image.png)
### 1.1  Server side:
#### 1.1.1. Requirement:
- The server is responsible for storing the list of files that are available for sharing and from which client that file is available. (Note that the server doesn't really store the files)
- The server is also responsible for keeping track of the clients that are connected to the server.
- The server must allow concurrent connections from multiple clients.
- The server must be able to handle multiple clients at the same time.
- The server must be able to handle multiple requests from the same client at the same time.
- The server must be able to handle multiple requests from different clients at the same time.
- The server must be able to handle multiple requests from different clients for the same file at the same time.
#### 1.1.2. Implementation:
- The server is implemented  as a multi-threaded server.
- The server opens thread and listen for connection from the clients. 
- The server  store necessary information of clients (IP, port, files).
- The server sends to clients the list of files that are available for sharing.
- The server open a listener for each client to listen for the request from the client.
- Then the server process the request and handle the request.
- The server doesn't a UI, it can just be a console application.
### 1.2  Client side:
#### 1.2.1. Requirement:
- The client must be able to connect to the server.
- The client must be able to send a request to the server to get the list of files that are available for sharing.
- The client must be able to send a request to the server to download a file.
- The client must be able to download a file from another client.
- The client must be able to share a file with other clients.
- The client must be able to download a file from multiple clients at the same time.
- Sending files and receiving files can be done concurrently.
#### 1.2.2. Implementation:
- The client is implemented also a multi-threaded client. (tho the client doesn't open threads all the time)
- When the client starts, it connects to the server by the input IP got from the login form.
- The client sends a request to the server to get the list of files that are available for sharing.
- When the user want to download a file, the client sends a request to the server and open a connection listener for the connection of clients that have the file.
- When the clients receive request for a file, it open a thread that handled the sending of the file.
## 2. Things have and haven't been implemented
### 2.1. Server side:
#### 2.1.1 Things have been implemented:
- [x] Multi-thread of the server. The server can handle multiple clients at the same time.
- [x] The server can obtain the list of files from the clients.
- [x] The server can handle request for list of files from the clients.
- [x] The server can handle requests for downloading a file for clients.
- [x] The server can handled when exception occurs.
#### 2.1.2 Things haven't been implemented:
- [ ] Sending message to ensure the client is online or not. 
- [ ] Know when the client is offline, remove and reset the information of the client.
### 2.2. Client side: 
#### 2.2.1 Things have been implemented:
- [x] The client can connect to the server.
- [x] Multi-thread of the client. The client can handle multiple requests at the same time.
- [x] The client can send a request to the server to get the list of files that are available for sharing.
- [x] The client can send a request to the server to download a file.
- [x] The client can download a file from another client.
- [x] The client can share a file with other clients.
#### 2.2.2 Things haven't been implemented:
- [ ] Checksum of the file to ensure the file is not corrupted.
- [ ] Handshake protocol to ensure the receiving client is online and is the right clients.
- [ ] BandWidth control for file transfering.
## 3. How to run the project
1. Clone the project to your local machine.
2. You'll need to run the Server first then run the Client(s).
### 3.1. Run the Server
1. Open the project by your favorite IDE (IntelliJ IDEA, Eclipse, NetBeans, ...)
2. Compile the server: 
```bash abc
mvn compile
```
3. Run the server:
```bash
mvn exec:java -Dexec.mainClass="Server.Server"
```
4. The server will print the IP that it running on. Note that IP for login of the client.
5. The server will ask for the amount of concurrent clients that you want to handle.
6. The server now can work.
### 3.2. Run the Client
1. Open the project by your favorite IDE (IntelliJ IDEA, Eclipse, NetBeans, ...)
2. You can run the client directly by this command  
```bash
mvn clean javafx:run
```
3. Enter the IP that the server is running on.
4. Fill in all the information and click login.
5. The client now can work.

## 4. Demo
You can see the demo and report of the project here: [Demo](https://drive.google.com/drive/folders/1I9FLaucZFaT99WejmJCfSGfJnf3MfJja)


