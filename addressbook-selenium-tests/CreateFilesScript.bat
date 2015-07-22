del groups.txt
del groups.xml
del contacts.txt
del contacts.xml
java -cp bin com.example.tests.GroupDataGenerator 10 groups.txt csv
java -cp bin;C:\Users\elabkovskaya\Downloads\xstream-distribution-1.4.8-bin\xstream-1.4.8\lib\xstream-1.4.8.jar;C:\Users\elabkovskaya\Downloads\xstream-distribution-1.4.8-bin\xstream-1.4.8\lib\xstream\xpp3_min-1.1.4c.jar com.example.tests.GroupDataGenerator 10 groups.xml xml
java -cp bin com.example.tests.ContactDataGenerator 10 contacts.txt csv
java -cp bin;C:\Users\elabkovskaya\Downloads\xstream-distribution-1.4.8-bin\xstream-1.4.8\lib\xstream-1.4.8.jar;C:\Users\elabkovskaya\Downloads\xstream-distribution-1.4.8-bin\xstream-1.4.8\lib\xstream\xpp3_min-1.1.4c.jar com.example.tests.ContactDataGenerator 10 contacts.xml xml


