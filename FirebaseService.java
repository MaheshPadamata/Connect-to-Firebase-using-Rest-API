package com.service;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers.CalendarDeserializer;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.object.Person;
import com.service.sendSMS;

	@Service
	public class FirebaseService {
		
	    public String saveUserDetails(Person person) throws InterruptedException, ExecutionException {
	        Firestore dbFirestore = FirestoreClient.getFirestore();
	        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("Test").document(person.getName()).set(person);
	        return collectionsApiFuture.get().getUpdateTime().toString();
	    }
	    
	    
	    //get Entire data of Collection
	    public List<Person> getAllDetails() throws InterruptedException, ExecutionException{
	    	Firestore dbFirestore = FirestoreClient.getFirestore();
	    	List<Person> personList=new ArrayList<Person>();
	    	CollectionReference alldetails=dbFirestore.collection("Test");
	    	ApiFuture<QuerySnapshot>  querySnapshot=alldetails.get();
	    	for(DocumentSnapshot docsnapshot:querySnapshot.get().getDocuments()) {
	    		Person perObject=docsnapshot.toObject(Person.class);
	    		personList.add(perObject);
	    	}
			return personList;
	    }
	    
	  //get Entire Phone numbers data of Collection
	    public List<Long> getAllphoneNumberDetails() throws InterruptedException, ExecutionException{
	    	Firestore dbFirestore = FirestoreClient.getFirestore();
	    	List<Long> phnolist=new ArrayList<Long>();
	    	CollectionReference alldetails=dbFirestore.collection("Test");
	    	ApiFuture<QuerySnapshot>  querySnapshot=alldetails.get();
	    	
	    	SimpleDateFormat dateformate=new SimpleDateFormat("dd-MMM-yyyy");
    		Date date=new Date();
    		Calendar cal=Calendar.getInstance();
    		cal.setTime(date);
    		//cal.add(Calendar.DAY_OF_YEAR,-1);
    		String currentdate=dateformate.format(cal.getTime());
	    	for(DocumentSnapshot docsnapshot:querySnapshot.get().getDocuments()) {
	    		Person perObject=docsnapshot.toObject(Person.class);
	    		try {
	    		String registerdate=dateformate.format(dateformate.parse(perObject.getBookdate()));
	    		if(dateformate.parse(registerdate).equals(dateformate.parse(currentdate))) {
	    			phnolist.add(perObject.getPhonenumber());
	    		}
	    		}catch(Exception e) {
	    			e.printStackTrace();
	    		}
	    	}
	    	for(int i=0; i<phnolist.size(); i++) {
	    		sendSMS.sendSms(phnolist.get(i).toString());
	    	}
			return phnolist;
	    }
	    
	    //get singal user data using document ID
	    public Person getUserDetails(String name) throws InterruptedException, ExecutionException {
	        Firestore dbFirestore = FirestoreClient.getFirestore();
	        DocumentReference documentReference = dbFirestore.collection("Test").document(name);
	        ApiFuture<DocumentSnapshot> future = documentReference.get();
	        DocumentSnapshot document = future.get();
	        Person person = null;
	        if(document.exists()) {
	            person = document.toObject(Person.class);
	            return person;
	        }else {
	            return null;
	        }
	    }
	    
	  //get singal user data using column ID
	    public Person getspecificUserDetails(String name) throws InterruptedException, ExecutionException {
	        Firestore dbFirestore = FirestoreClient.getFirestore();
	        DocumentReference documentReference = dbFirestore.collection("Test").document(name);
	        ApiFuture<DocumentSnapshot> future = documentReference.get();
	        DocumentSnapshot document = future.get();
	        Person person = null;
	        if(document.exists()) {
	            person = document.toObject(Person.class);
	            return person;
	        }else {
	            return null;
	        }
	    }
	    
	    //get All users data
	    /*public Person getUserAllDetails(String name) throws InterruptedException, ExecutionException {
	    	Person person = null;
	    	Firestore dbFirestore = FirestoreClient.getFirestore();
	        Iterable<DocumentReference> documentReference = dbFirestore.collection("Test").listDocuments();
	        ApiFuture<QuerySnapshot> future = ((Query) documentReference).get();
	        QuerySnapshot document = future.get();
	        
	        System.out.println("step1");
	        if(document.) {
	            person = (List<Person>) document.toObject(Person.class);
	            return person;
	        }else {
	            return null;
	        }
	    }*/


	    /*public String updateUserDetails(Person person) throws InterruptedException, ExecutionException {
	        Firestore dbFirestore = FirestoreClient.getFirestore();
	        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("users").document(person.getName()).set(person);
	        return collectionsApiFuture.get().getUpdateTime().toString();
	    }

	    public String deleteUser(String name) {
	        Firestore dbFirestore = FirestoreClient.getFirestore();
	        ApiFuture<WriteResult> writeResult = dbFirestore.collection("users").document(name).delete();
	        return "Document with ID "+name+" has been deleted";
	    }*/
}
