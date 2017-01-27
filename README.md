# TCP Nukleus Specification

[![Build Status][build-status-image]][build-status]

[build-status-image]: https://travis-ci.org/reaktivity/nukleus-tcp.spec.svg?branch=develop
[build-status]: https://travis-ci.org/reaktivity/nukleus-tcp.spec

## Overview

The TCP nukleus communicates with the operating system TCP/IP layer. It is used to accept incoming socket connections and establish outgoing ones and send and receive data over those connections.  Its job is to:
1. read data from a streams file that it owns (known as a source) and write it out to the network, and
2. read data off the network and write it to a streams file owned by another nukleus (known as the target).

There can be multiple sources and targets if the TCP nukleus is being used in conjunction with multiple other nuklei.

## Control protocol

In ROUTE INPUT control commands, the source is interpreted as an interface name and sourceRef as a port number.
The route command extension, TcpRouteEx, is used to discriminate which device addresses to listen on. The ip address is activing as a filter (constraint). 

## Specification scripts

These are grouped as follows:

- control: route and unroute commands sent between a controller and the tcp nukleus

- streams: for each scenario the scripts are grouped as follows:
  - server: when the TCP nukleus is acting as a server 
    - target.rpt: bytes the upstream nukleus (e.g. http) should observe
    - nukleus.rpt: what the TCP nukleus does to write those bytes
  - client: when the tcp nukleus is acting as a client
    - source.rpt: bytes written by the upstream nukleus
    - nukleus.rpt: what the TCP nukleus does to read those bytes
    