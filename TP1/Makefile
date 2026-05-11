CXX = g++
CXXFLAGS = -Wall -Wextra -std=c++11

all: multicast_receiver multicast_sender

multicast_receiver: src/multicast_receiver.cpp
	$(CXX) $(CXXFLAGS) -o $@ $^

multicast_sender: src/multicast_sender.cpp
	$(CXX) $(CXXFLAGS) -o $@ $^

clean:
	rm -f multicast_receiver multicast_sender