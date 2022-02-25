#!/bin/zsh

#1.for1
#for((初始值;循环控制条件;变量变化))
#do
#    程序
#done

#for2
#for 变量 in {值1...值2}
#do
#  程序
#done
s=0
for((i=1;i<=100;i++))
do
  s=$[$s+$i]
done
echo $s


for k in $*
do
  echo "zoujiahao is doing $k"
done

for x in $@
do
  echo "zoujiahao is doing $x"
done

#区别"$@"和"$*" "$*" 表示连着所有 "$@"表示分开所有
for y in "$*"
do
  echo "zoujiahao is doing $y"
done

for z in "$@"
do
  echo "zoujiahao is doing $z"
done
