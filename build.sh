#remove old build
if [ -d "build" ];then
	echo -e "\n\033[1;31mRemoving old build\033[0m";
	rm -r build
fi

#create build directory
echo -e "\n\033[1;31mDirectory creation\033[0m";
mkdir build

#compilation
echo -e "\n\033[1;31mCompilation\033[0m";
mkdir build/class
javac -sourcepath src src/vetris/Main.java -d build/class -version

#archiving
echo -e "\n\033[1;31mArchiving\033[0m";
mkdir build/release
jar cvmf MANIFEST.MF build/release/Vetris.jar -C build/class .

#end
echo -e "\n\033[1;31mEnd\033[0m\n";
