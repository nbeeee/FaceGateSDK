# FaceGateSDK
人脸闸机java SDK
1、系统windows 64位
2、JDK使用java1.8 64位
3、使用Idea开发工具
4、将FaceGateApi.dll 任意文件夹path
	FaceGateApi instance = Native.loadLibrary(path + "FaceGateApi.dll",FaceGateApi.class);
5、使用maven管理的jar包，也可以直接将jna的jar包添加到buildpath中
