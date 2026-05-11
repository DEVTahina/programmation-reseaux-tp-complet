// multicast_receiver_windows.cpp
#ifndef _WIN32_WINNT
#define _WIN32_WINNT 0x0A00  // Windows 10/11
#endif
#ifndef NTDDI_VERSION
#define NTDDI_VERSION NTDDI_WIN10  // Version Windows 10
#endif

#include <iostream>
#include <cstring>
#include <winsock2.h>
#include <ws2tcpip.h>

#pragma comment(lib, "ws2_32.lib")

#define BUFFER_SIZE 1024

int main(int argc, char* argv[]) {
    if(argc != 3) {
        std::cerr << "Usage: " << argv[0] << " <multicast_ip> <port> " << std::endl;
        return 1;
    }

    //Initialisation Winsock
    WSADATA wsaData;

    if (WSAStartup(MAKEWORD(2,2), &wsaData) != 0) {
        std::cerr << "WSAStartup failed" << std::endl;
        return 1;
    }

    const char* multicast_addr = argv[1];
    int port = std::stoi(argv[2]);

    SOCKET sock = socket(AF_INET, SOCK_RDM, 0);
    if (sock == INVALID_SOCKET){
        std::cerr << "socket error: " << WSAGetLastError() << std::endl;
        WSACleanup();
        return 1;
    }

    // Autoriser le partage d'adresse (reuse)
    BOOL reuse = TRUE;
    if(setsockopt(sock, SOL_SOCKET, SO_REUSEADDR, (char*) &reuse, sizeof(reuse)) == SOCKET_ERROR) {
        std::cerr << "setsockopt reuse error : " << WSAGetLastError() << std::endl;
        closesocket(sock);
        WSACleanup();
        return 1;
    }

    struct sockaddr_in addr;
    memset(&addr, 0, sizeof(addr));
    addr.sin_family = AF_INET;
    addr.sin_port = htons(port);
    addr.sin_addr.s_addr = INADDR_ANY;

    if (bind(sock, (struct sockaddr*)&addr, sizeof(addr)) == SOCKET_ERROR) {
        std::cerr << "bind error: " << WSAGetLastError() << std::endl;
        closesocket(sock);
        WSACleanup();
        return 1;
    }


        // Rejoindre le groupe multicast
    struct ip_mreq mreq;
    mreq.imr_multiaddr.s_addr = inet_addr(multicast_addr);
    mreq.imr_interface.s_addr = INADDR_ANY;

    if (setsockopt(sock, IPPROTO_IP, IP_ADD_MEMBERSHIP, (char*)&mreq, sizeof(mreq))==SOCKET_ERROR) {
        std::cerr << "setsockopt add membership error: " <<WSAGetLastError() << std::endl;
        closesocket(sock);
        WSACleanup();
        return 1;
    }

    std::cout << "Joind multicast group " << multicast_addr << ":" << port << std::endl;
    char buffer[BUFFER_SIZE];
    struct sockaddr_in sender_addr;
    int sender_len = sizeof(sender_addr);

    while (true)
    {
        int n = recvfrom(sock, buffer, BUFFER_SIZE, 0, (struct sockaddr*)&sender_addr, &sender_len);
        if (n == SOCKET_ERROR) {
            std::cerr << "recvfrom error: " << WSAGetLastError() << std::endl;
            break;
        }

        buffer[n] = '\0';
        std::cout << "Received " << n << "bytes from " << inet_ntoa(sender_addr.sin_addr) << ": " << buffer << std::endl;
    }

     // Quitter le groupe
    setsockopt(sock, IPPROTO_IP, IP_DROP_MEMBERSHIP, (char*)&mreq, sizeof(mreq));
    closesocket(sock);
    WSACleanup();
    
    return 0;
}