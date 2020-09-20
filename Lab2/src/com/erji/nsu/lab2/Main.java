package com.erji.nsu.lab2;

public class Main {

    public static void main(String[] args) {
        try {
            Calculator calculator = new Calculator();
            calculator.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
//5 dup [ dup dup [ print 1 - dup ] drop 1 - dup ]

/*
[define, min, dup, rot, dup, rot, 1, rot, rot]
 */

/*
define min
  dup rot dup rot 1 rot rot  <
  [ drop swap drop 0 0 ]
  [ drop 0 ] ;
5 7 min print

define min
  dup rot dup rot 1 rot rot  <
  [ drop swap drop 0 0 ] ;
7 5 min print
*/