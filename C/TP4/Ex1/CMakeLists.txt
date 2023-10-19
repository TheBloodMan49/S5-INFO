cmake_minimum_required(VERSION 3.27)
project(ProjTache C)

set(CMAKE_C_STANDARD 90)

set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -Wall -Wextra -pedantic-errors")

add_executable(Ex1 main.c
        tache.h
        tache.c)
