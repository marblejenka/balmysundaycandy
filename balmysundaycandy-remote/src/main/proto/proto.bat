cd C:\development\workspaces\appengine\balmysundaycandy-scala\src-proto
protoc -I=. --java_out=../src-java RemoteCallRequest.proto
protoc -I=. --java_out=../src-java RemoteCallResponse.proto
pause