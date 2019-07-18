#  requires
implementation 'com.google.firebase:firebase-messaging:19.0.1'


# manifest
```
<service
    android:name=".FirebaseMessagingServiceHandler"
    android:exported="false">
    <intent-filter>
        <action android:name="com.google.firebase.MESSAGING_EVENT" />
    </intent-filter>
</service>
```


# structure
```
{
    "to": "dgxOHmdrclU:APA91bG19_J75UF3-1W-RhZt8vQh3sS...",
    "notification": {
        "title": "my title",
        "body": "my message"
    },
    "data": {
        "title": "my title",
        "message": "my message",
        "action": "url",
        "action_destination": "http://androiddeft.com"
    }
}


or 'to' => '/topics/xxx'  
```


## example 
```
 // Set POST variables
$url = 'https://fcm.googleapis.com/fcm/send';
 
$headers = array(
	'Authorization: key=' . $firebase_api,
	'Content-Type: application/json'
);

// Open connection
$ch = curl_init();
 
// Set the url, number of POST vars, POST data
curl_setopt($ch, CURLOPT_URL, $url);
 
curl_setopt($ch, CURLOPT_POST, true);
curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
 
// Disabling SSL Certificate support temporarily
curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
 
curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($fields));
 
// Execute post
$result = curl_exec($ch);
```
