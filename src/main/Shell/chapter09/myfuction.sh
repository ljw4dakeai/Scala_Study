#!/bin/bash

#使用函数前需要先定义函数 shell是一行一行执行的，不会先编译

#基本语法
# [ fuiction ] fuctionname[()]
# {
#   Action;
#   [returrn int;]
# }
# fuctionname
#

# shellcheck disable=SC1088
sum()
{
  s=0;
  s=$[$1+$2]
  echo $s
}

read -p "请输入你的第一个参数" p1
read -p "请输入你的第二个参数" p2
sum $p1 $p2