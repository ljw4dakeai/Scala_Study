#!/bin/zsh

#基本语法
#"$((运算))"   "$[]"
# exper + - \* / % 运算符间要求有空格
expr 3 + 2
expr `expr 2 + 3` \* 4

s=$[(2+3)*4]
echo $s

x=$(((2+3)*4))
echo $x

