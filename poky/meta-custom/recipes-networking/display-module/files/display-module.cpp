//Updated

#include <iostream>
#include <cstring>
#include <arpa/inet.h>
#include <unistd.h>
#include <sstream>

#define PORT 8081
#define BUFFER_SIZE 1024

void displayMessage(const std::string &message) {
    std::cout << "Displaying message: " << message << std::endl;

    std::istringstream iss(message);
    std::string timestamp, pid, hostname, errorType;
    if (iss >> timestamp >> pid >> hostname >> errorType) {
        std::cout << "Timestamp: " << timestamp << std::endl;
        std::cout << "Process ID: " << pid << std::endl;
        std::cout << "Hostname: " << hostname << std::endl;
        std::cout << "Error Type: " << errorType << std::endl;
    } else {
        std::cerr << "Error parsing message." << std::endl;
    }
}

int main() {
    int sockfd;
    char buffer[BUFFER_SIZE];
    struct sockaddr_in servaddr, cliaddr;

    // Creating socket file descriptor
    if ((sockfd = socket(AF_INET, SOCK_DGRAM, 0)) < 0) {
        perror("socket creation failed");
        exit(EXIT_FAILURE);
    }
    std::cout<<"Ready to Display the Data"<<std::endl;
    memset(&servaddr, 0, sizeof(servaddr));
    memset(&cliaddr, 0, sizeof(cliaddr));

    // Filling server information
    servaddr.sin_family = AF_INET; // IPv4
    servaddr.sin_addr.s_addr = INADDR_ANY;
    servaddr.sin_port = htons(PORT);

    // Bind the socket with the server address
    if (bind(sockfd, (const struct sockaddr *)&servaddr, sizeof(servaddr)) < 0) {
        perror("bind failed");
        close(sockfd);
        exit(EXIT_FAILURE);
    }

    int n;
    socklen_t len = sizeof(cliaddr);

    while (true) {
        n = recvfrom(sockfd, (char *)buffer, BUFFER_SIZE, MSG_WAITALL, (struct sockaddr *)&cliaddr, &len);
        buffer[n] = '\0';
        std::string message = buffer;

        displayMessage(message);
    }

    close(sockfd);
    return 0;
}