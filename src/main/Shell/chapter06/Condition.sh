#!/bin/zsh

#[]判断  空返回false 非空返回true

#-lt(less then)<   -le(less equal)<=
#-eq(equal)    =   -gt(greater than) >
#-ge(greater than) >= -ne(not equal) !=

#-r(read) 可度 -w(write)可写 -x(execute)执行
#-f(file)是否为文件 -e(existence) 是否存在 -d(directory) 是否为一个目录

[ 23 -ge -23 ]
echo $? #正确 ==>0
[ 23 -le -23 ]
echo $? #错误 ==>!1

#&&上一条成功后才执行下一条
#||上一条执行失败后才执行下一条