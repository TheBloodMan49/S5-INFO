cmake_minimum_required(VERSION 3.27)
project(Ex1 C)

set(CMAKE_C_STANDARD 90)

set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -Wall -Wextra -Werror -pedantic-errors")

add_executable(Ex1
        principal.c
        fichier.c
        fichier.h
        traitement.c
        traitement.h
        traitementOpt.c)
