package com.kaushikrana.ifcs;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.*;

import javax.xml.transform.ErrorListener;

public class MainActivity extends AppCompatActivity {


    private String[] STORED_BANK_LIST = {"- - - - - Select Bank - - - - -","510 Army Base W/s Credit Co-operative Primary Bank","A.P. Mahesh Co-operative Urban Bank","AB Bank","Abhinandan Urban Co-operative Bank Amravati","Abhinav Sahakari Bank","Abhyudaya Co-operative Bank","Ace Co-operative Bank","Adarniya P.d. Patilsaheb Sahakari Bank","Adarsh Co-operative Bank","Adarsh Co-operative Urban Bank","Adarsh Mahila Mercantile Co-operative Bank ","Adarsh Mahila Nagari Sahakari Bank ","Adilabad District Co-operative Central Bank ","Adv.shamraoji Shinde Satyashodhak Bank ","Agartala Co-operative Urban Bank ","Agra District Co-operative Bank ","Agrasen Co-operative Urban Bank ","Agrasen Nagari Sahakari Bank ","Agroha Co-operative Urban Bank ","Ahilyadevi Urban Co-operative Bank Limited Solapur ","Ahmedabad District Co-operative Bank ","Ahmedabad Mercantile Co-operative Bank ","Ahmednagar District Central Co-operative Bank ","Ahmednagar Merchants Co-operative Bank ","Ahmednagar Merchant's Co-operative Bank ","Ahmednagar Shahar Sahakari Bank Maryadit ","Ahmednagar ZPSS Bank ","Airtel Payments Bank ","Ajantha Urban Co-operative Bank ","Ajara Urban Co-operative Bank ","Ajmer Central Co-operative Bank ","Akhand Anand Co.op Bank ","Akkamahadevi Mahila Sahakari Bank Niyamit ","Akola District Central Co-operative Bank ","Akola Janata Commercial Co-operative Bank ","Akola Merchant Co-operative Bank ","Akola Urban Co-operative Bank ","Alappuzha District Co-operative Bank ","Alavi Co-operative Bank ","Aligarh District Co-operative Bank ","Allahabad Bank ","Allahabad District Co-operative Bank ","Almora Urban Co-operative Bank ","Almora Zila Sahkari Bank ","Alwar Central Co-operative Bank ","Alwaye Urban Co-operative Bank ","Amalner Urban Co-operative Bank ","Aman Sahakari Bank ","Amarnath Co-operative Bank ","Ambajogai Peoples Co-operative Bank ","Ambala Central Co-operative Bank ","Ambarnath Jai-hind Co-operative Bank ","Ambarnath Jaihind Co-operative Bank Ambarnath ","Ambika Mahila Sahakari Bank Ahmednagar ","Amravati District Central Co-operative Bank ","Amravati Merchants Co-operative BANK ","Amreli Jilla Madhyastha Sahakari Bank ","Amreli Nagarik Sahakari Bank ","Amritsar Central Co-operative Bank ","Anand Mercantile Co-operative Bank ","Anantapur District Co-operative Central Bank ","Andaman & Nicobar State Co-operative Bank ","Andhra Bank ","Andhra Pradesh Grameena Vikas Bank ","Andhra Pradesh State Co-operative Bank ","Andhra Pragathi Grameena Bank ","Anendeshwari Nagrik Sahakari Bank ","Angul United Central Co-operative Bank ","Ankola Urban Co-operative Bank ","Annasaheb Chougule Urban Co-operative Bank ","Annasaheb Magar Sahakari Bank ","Annasaheb Savant Co-operative Urban Bank Mahad ","Anuradha Urban Co-operative Bank ","Ap Janata Co-operative Urban Bank ","Ap Mahajan's Co-operative Urban Bank ","Apani Sahakari Bank ","Apna Sahakari Bank ","Arunachal Pradesh Rural Bank ","Arunachal Pradesh State Co-operative Apex Bank ","Arvind Sahakari Bank ","Aryapuram Co-operative Urban Bank ","Ashok Sahakari Bank ","Ashoknagar Co-operative Bank ","Ashta People's Co-operative Bank ","Aska Co-operatiave Central Bank ","Assam Co-operative Apex Bank ","Assam Gramin Vikash Bank ","Associate Co-operative Bank ","Astha Mahila Nagrik Sahakari Bank Maryadit ","AU Small Finance Bank ","Aurangabad District Central Co-operative Bank ","Aurangabad District Central Co-operative Bank. Bihar ","Australia and New Zealand Banking Group ","Axis Bank ","Azad Co-operative Bank ","Azad Urban Co-operative Bank Hubli ","Badagara Co-operative Urban Bank ","Badaun Zila Sahkari Bank ","Bagalkot District Central Co-operative Bank ","Baghat Urban Co-operative Bank ","Bahraich District Co-operative Bank", "Baidyabati Sheoraphuli Co-operative Bank ","Balageria Central Co-operative Bank ","Balangir District Central Co-operative Bank ","Balasinor Nagarik Sahakari Bank ","Balasore Bhadrak Central Co-operative Bank ","Balitikuri Co-operative Bank ","Ballari District Co-operative Central Bank ","Balotra Urban Co-operative Bank ","Balusseri Co-operative Urban Bank ","Banaras Merchantile Co-operative Bank ","Banaskantha Dist Central Co-operative Bank ","Banaskantha Mercantile Co-operative Bank ","Banda District Co-operative Bank Limited Banda ","Banda Urban Co-operative Bank ","Bandhan Bank ","Bangalore  Bangalore Rural&ramanagara Dccb ","Bangalore City Co-operative Bank ","Bangiya Gramin Vikash Bank ","Bank of America ","Bank of Bahrein and Kuwait ","Bank of Baroda ","Bank of Ceylon ","Bank of India ","Bank of Maharashtra ","Bank of Nova Scotia ","Banki Central Co-operative Bank ","Bankura District Central Co-operative Bank ","Banswara Central Co-operative Bank ","Bantra Co-operative Bank ","Bapuji Co-operative Bank ","Bapunagar Mahila Co-operative Bank ","Baramati Sahakari Bank ","Baramulla Central Co-operative Bank ","Baran Kendriya Sahakari Bank Baran ","Baran Nagrik Sahkari Bank ","Barclays Bank ","Barmer Central Co-operative Bank ","Baroda Central Co-operative Bank ","Baroda City Co-operative Bank ","Baroda Gujarat Gramin Bank ","Baroda Rajasthan Kshetriya Gramin Bank ","Baroda Traders Co-operative Bank ","Baroda Uttar Pradesh Gramin Bank ","Bassein Catholic Co-operative Bank ","Bathinda Central Co-operative Bank ","Bavla Nagrik Sahakari Bank ","Beawar Urban Co-operative Bank ","Becharaji Nagarik Sahakari Bank ","Beed District Central Co-operative Bank ","Begusarai Central Co-operative Bank ","Belgaum District Central Co-operative Bank ","Bellad Bagewadi Urban Souharada Sahakari Bank Nyt ","Bellary District Co-operative Central Bank ","Belur Urban Co-operative Bank ","Betul Nagrik Sahakari Bank Mydt ","Bhabhar Vibhag Nagrik Sahakari Bank ","Bhadgaon People's Co-operative Bank ","Bhadohi Urban Co-operative Bank Gyanpur ","Bhadradri Co-operative Urban Bank ","Bhadran People's Co-operative Bank ","Bhagalpur Central Co-operative Bank ","Bhagini Nivedita Sahakari Bank ","Bhagini Nivedita Sahakari Bank Pune ","Bhagyalakshmi Mahila Sahakari Bank ","Bhagyodaya Co-operative Bank ","Bhandara District Central Co-operative Bank ","Bhandara Urban Co-operative Bank ","Bharat Co-operative Bank ","Bharath Co-operative Bank ","Bharati Sahakari Bank Pune ","Bharatpur Central Co-operative Bank ","Bharuch District Central Co-operative Bank Bharuc ","Bhatkal Urban Co-operative Bank ","Bhatpara Naihati Co-operative Bank ","Bhavana Rishi Co-operative. Urban Bank ","Bhavani Sahakari Bank ","Bhavasara Kshatriya Co-operative Bank ","Bhavnagar District Co-operative Bank ","Bhawanipatna Central Co-operative Bank ","Bhilai Nagarik Sahakari Bank Maryadit ","Bhilwara Urban Co-operative Bank ","Bhind Nagrik Sahakari Bank Mydt ","Bhingar Urban Co-operative Bank ","Bhiwani Central Co-operative Bank Bhiwani ","Bhopal Co-operative Central Bank", "Bhuj Commercial Co-operative Bank ","Bhuj Mercentile Co-operative Bank ","Bicholim Urban Co-operative Bank ","Bihar Awami Co-operative Bank ","Bihar State Co-operative Bank ","Bijapur District Central Co-operative Bank ","Bijnor Urban Co-operative Bank ","Bilagi Pattana Sahakari Bank Niyamit ","BNP Paribas Bank ","Bombay Mercantile Co-operative Bank ","Boral Union Co-operative Bank ","Botad Peoples Co-operative Bank ","Boudh Co-operative Central Bank ","BOUDH Co-operative Central Bank ","Brahmadeodada Mane Sahakari Bank Solapur ","Bramhapuri Urban Co-operative Bank ","Buldana District Central Co-operative Bank ","Bundi Central Co-operative Bank ","Burdwan Central Co-operative Bank ","Business Co-operative Bank ","Calicut Co-operative Urban Bank ","Canara Bank ","Capital Small Finance Bank ","Catholic Syrian Bank ","Central Bank of India ","Central Co-operative Bank  Bhilwara ","Central Co-operative Bank Ara ","Central Co-operative Bank Bikaner ","Central Co-operative Bank Tonk ","Chaitanya Godavari Grameena Bank ","Chaitanya Mahila Sahakari Bank, Vijayapur ","Chamba Urban Co-operative Bank Chamba ","Chamoli Zila Sahkari Bank ","Chanasma Commercial Co-operative Bank ","Chandigarh State Co-operative Bank ","Chandrapur District Central Co-operative Bank ","Chartered Sahakari Bank Niyamitha ","Chembur Nagarik Sahakari Bank ","Chennai Central Co-operative Bank ","Cherpalcheri Co-operative Urban Bank","Chhattisgarh Gramin Bank ","Chhattisgarh Rajya Sahakari Bank Mydt ","Chikhli Urban Co-operative Bank ","Chikmagalur District Central Co-operative Bank ","Chikmagalur Pattana Sahakara Bank Niyamitha ","Chinatrust Commercial Bank ","Chiplun Urban Co-operative Bank ","Chitnavispura Sahakari Bank ","Chitradurga District Co-operative Central Bank ","Chittoor District Co-operative Central Bank ","Chittorgarh Kendriya Sahakari Bank ","Chittorgarh Urban Co-operative Bank Chittorgarh ","Chopda Peoples Co-operative Bank ","Churu Central Co-operative Bank ","Churu Zila Urban Co-operative Bank ","CITI Bank ","Citizen Co-operative Bank","Citizen Credit Co-operative Bank ","Citizens Co-operative Bank ","Citizens' Co-operative Bank Jammu ","Citizens Urban Co-operative Bank ","City Union Bank ","Coastal Local Area Bank ","Coimbatore District Central Co-operative Bank ","Col Rd Nikam Sainik Sahakari Bank ","Colour Merchant's Co-operative Bank ","Commercial Co-operative Bank ","Contai Co-operative Bank ","Co-operative Bank of Mehsana ","Co-operative Bank of Rajkot ","Co-operative City Bank ","Corporation Bank ","Cosmos Co-operative Bank ","Credit Agricole Corporate and Investment Bank ","Credit Suisse AG","Cuddalore District Central Co-operative Bank ","Cuttack Central Co-operative Bank ","D.y.patil Sahakari Bank Kolhapur ","Dahod Mercantile Co-operative Bank ","Dahod Urban Co-operative Bank ","Daivadnya Sahakara Bank Niyamit ","Dakshin Bihar Gramin Bank ","Dakshin Dinajpur District Central Co-operative Bank ","Dapoli Urban Co-operative Bank ","Darjeeling District Central Co-operative Bank ","Darussalam Co-operative Urban Bank ","Dattatraya Maharaj Kalambe Jaoli Sahakari Bank ","Daund Urban Co-operative Bank","Dausa Kendriya Sahkari Bank Dausa ","Dausa Urban Co-operative Bank ","Davanagere District Central Co-operative Bank ","DCB Bank ","Deccan Co-operative Urban Bank ","Deccan Merchants Co-operative Bank ","Deendayal Nagari Sahakari Bank ","Defence Accounts Co-operative Bank ","Delhi Nagrik Sehkari Bank ","Delhi State Co-operative Bank ","Dena Bank ","Deogiri Nagari Sahakari Bank Aurangabad ","Deogiri Sahakari Bank Aurangabad ","Deoria Kasia District Co-operative Bank ","Deposit Insurance and Credit Guarantee Corporation ","Deutsche Bank ","Development Bank of Singapore ","Development Co-operative Bank Kanpur ","Devika Urban Co-operative Bank ","Dhakuria Co-operative Bank ","Dhanbad Central Co-operative Bank ","Dhanera Mercantile Co-operative Bank ","Dhanlaxmi Bank ","Dharamvir Sambhaji Urban Co-operative Bank ","Dharmapuri District Central Co-operative Bank ","Dhule And Nandurbar District Central Co-operative Bank ","Dilip Urban Co-operative Bank ","Dindigul Central Co-operative Bank ","District Central Co-operative Bank Khammam ","District Central Co-operative Bank Limited Elluru","District Co-operative Bank Barabanki ","District Co-operative Bank Dehradun ","District Co-Operative Bank Faizabad ","District Co-operative Bank Mainpuri ","District Co-operative Bank Pilibhit ","District Co-operative Bank Pratapgarh ","District Co-operative Bank Saharanpur ","District Co-operative Bank Shahjahanpur ","District Co-operative Bank Teliyabagh ","District Co-operative Bank, Sitapur ","District Co-operative Bankraebareli ","District Co-operative Central Bank ","District Co-operative Central Bank Kakinada ","District Co-operative Central Bank Kurnool ","District Co-operative Central Bank Mahabubnagar ","District Co-operative Central Bank Srikakul ","District Co-operative Central Bank Visakhapatnam ","District Co-operative Central Bank Vizianagaram ","District Co-operative Central Bankmedak ","Dmk Jaoli Bank ","Doha Bank QSC ","Dombivli Nagari Sahakari Bank ","Dr Babasaheb Ambedkar Sahakari Bank Nasik ","Dr. Ambedkar Nagrik Sahakari Bank Mydt Gwalior ","Dungarpur Central Co-operative Bank ","Durgapur Mahila Co-operative Bank ","Durgapur Steel Peoples Co-operative Bank ","Durgapur Steel Peoples' Co-operative Bank ","Eenadu Co-operative Urban Bank ","Ellaquai Dehati Bank ","Emirates NBD Bank ","Equitas Small Finance Bank ","Ernakulam District Co-operative Bank ","Erode District Central Co-operative Bank ","Esaf Small Finance Bank ","ESAF Small Finance Bank ","Etah District Co-operative Bank ","Etah Urban Co-operative Bank ","Etawah District Co-operative Bank Etwah ","Etawah Urban Co-operative Bank Etawah ","Excellent Co-operative Bank ","Export Import Bank of India ","Faiz Mercantile Co-operative Bank, Nasik ","Faridabad Central Co-operative Bank ","Faridkot Central Co-operative Bank ","Farrukhabad District Co-operative Bank Fatehgarh ","Fatehabad Central Co-operative Bank ","Fatehgrah Sahib Central Co-operative Bank ","Fazilka Central Co-operative. Bank ","Federal Bank ","Feroke Co-operative Bank ","Ferozepur Central Co-operative. Bank ","Financial Co-operative Bank ","Fincare Small Finance Bank ","Fingrowth Co-operative Bank ","Fino Payments Bank ","Firozabad Zila Sahkari Bank ","Firstrand Bank ","Gadchiroli District Central Co-operative Bank ","Gadhinglaj Urban Co-operative Bank ","Gandevi People's Co-operative Bank ","Gandhi Co-operative Urban Bank ","Gandhibag Sahakari Bank  Nagpur ","Gandhidham Co-operative Bank ","Gandhidham Mercantile Co-operative Bank ","Gandhinagar Nagrik Co-operative Bank ","Gandhinagar Urban Co-operative Bank ","Ganga Mercantile Urban Co-operative Bank ","Ganganagar Kendriya Sahakari Bank ","Gauhati Co-operative Urban Bank ","Gayatri Co-operative Urban Bank ","General Post Office ","Ghatal Peoples' Co-operative Bank ","Goa State Co-operative Bank ","Goa Urban Co-operative Bank ","Godavari Urban Co-operative Bank ","Godavari Urban Co-operative Bank Nashik ","Godhra Urban Co-operative Bank ","Gokak Urban Co-operative Credit Bank ","Gondal Nagarik Sahakari Bank ","Gondia District Central Co-operative Bank Gondia ","Gopalganj Central Co-operativeertative Bank ","Gopinath Patil Parsik Janata Sahakari Bank ","Gozaria Nagrik Sahakari Bank ","Grain Merchants' Co-operative Bank ","Gramin Bank of Aryavart ","Greater Bombay Co-operative Bank ","Guardian Souharda Sahakari Bank Niyamita ","Gujarat Ambuja Co-operative Bank ","Gujarat Mercantile Co-operative Bank ","Gujarat State Co-operative Bank ","Gulshan Mercantile Urban Co-operative Bank ","Guntur Co-operative Urban Bank ","Guntur District Co-operative Central Bank ","Gurdaspur Central Co-operative Bank ","Gurgaon Central Co-operative Bank ","Hadagali Urban Co-operative Bank ","Halol Mercantile Co-operative Bank ","Hamirpur District Co-operative Bank ","Hanamasagar Urban Co-operative Bank ","Hanumangarh Kendriya Sahakari Bank ","Hardoi District Co-operative Bank ","Harihareshwar Sahakari Bank ","Haryana State Co-operative Apex Bank ","Hassan District Co-operative Central Bank ","Hasti Co-operative Bank ","Haveli Sahakari Bank ","HCBL Co-operative Bank ","HDFC Bank ","Himachal Pradesh Gramin Bank ","Himachal Pradesh State Co-operative Bank ","Himatnagar Nagarik Sahakari Bank ","Hindustan Shipyard Staff Co-operative Bank ","Hindusthan Co-operative Bank ","Hisar Central Co-operative Bank Hisar ","Hissar Urban Co-operative Bank ","Hongkong & Shanghai Banking Corporation ","Hooghly Co-operative Credit Bank ","Hooghly District Central Co-operative Bank ","Hoshiarpur Central Co-operative Bank ","Howrah District Central Co-operative Bank ","HSBC Bank Oman S.A.O.G ","Hubli Urban Co-operative Bank ","Hutatma Sahakari Bank ","Hyderabad District Co-operative Bank ","Ichalkaranji Merchants Co-operative Bank ","ICICI Bank ","IDBI ","IDFC FIRST Bank ","Idukki District Co-operative Bank ","Ilkal Co-operative Bank ","Imperial Urban Co-operative Bank ","Imperial Urban Co-operative Bank Jalandhar ","Imphal Urban Co-operative Bank ","Income Tax Dept Co-operative Bank ","Indapur Urban Co-operative Bank ","Independence Co-operative Bank ","India Post Payments Bank ","Indian Bank ","Indian Clearing Corporation ","Indian Overseas Bank ","Indore Cloth Market Co-operative Bank ","Indore Paraspar Sahakari Bank ","Indore Premier Co-operative Bank Indore ","Indore Swayam Mahila Co-operative Bank ","Indraprastha Sehkari Bank ","Indusind Bank ","Industrial and Commercial Bank of China ","Industrial Bank of Korea ","Industrial Co-operative Bank ","Integral Urban Co-operative Bank ","Irinjalakuda Town Co-operative Bank ","J&K Grameen Bank ","J&k State Co-operative Bank ","Jagruti Co-operative Urban Bank ","Jaihind Urban Co-op Bank ","Jain Co-operative Bank ","Jain Sahakari Bank ","Jaipur Central Co-operative Bank ","Jaisalmer Central Co-operative Bank ","Jalandhar Central Co-operative Bank ","Jalaun District Co-operative Bank ","Jalgaon District Central Co-operative Bank Jalgaon ","Jalgaon Janata Sahkari Bank ","Jalgaon Peoples Co-operative Bank ","Jalna District Central Co-operative Bank ","Jalna Merchants Co-operative Bank ","Jalna Peoples Co-operative Bank Jalna ","Jalore Central Co-operative Bank Jalore ","Jalore Nagrik Sahakari Bank ","Jalpaiguri Central Co-operative Bank ","Jamia Co-operative Bank ","Jamkhandi Urban Co-operative Bank ","Jammu and Kashmir Bank ","Jamnagar District Co-operative Bank ","Jamnagar Mahila Sahakari Bank ","Jamnagar Peoples Co-operative Bank ","Jamshedpur Urban Co-operative Bank ","Jana Small Finance Bank ","Janakalyan Co-operative Bank Nashik ","Janakalyan Sahakari Bank ","Janalaxmi Co-operative Bank ","Janaseva Co-operative Bank (Nashik) ","Janaseva Sahakari Bank ","Janaseva Sahakari Bank (Borivli) ","Janata Co Op Bank ","Janata Co-operative Bank ","Janata Co-operative Bank Malegaon. ","Janata Co-operative Bank Sadalga ","Janata Sahakari Bank (Pune) ","Janata Sahakari Bank Ajara ","Janata Sahakari Bank Amravati ","Janatha Seva Co-operative Bank ","Janseva Nagari Sahakari Bank Marydit ","Jansewa Urban Co-operative Bank ","Jaynagar Mozilpur Peoples Co-operative Bank ","Jaysingpur Udgaon Sahakari Bank Jaysingpur ","Jhajjar Central Co-operative Bank ","Jhalawar Kendriya Sahkari Bank ","Jhalawar Nagrik Sahakari Bank ","Jharkhand State Co-operative Bank ","Jharneshwar Nagrik Sahakari Bank Maryadit ","Jhunjhunu Kenddriya Sahakari Bak ","Jijamata Mahila Sahakari Bank ","Jila Sahakari Bank Mydt. Gwalior ","Jila Sahakari Kendariya Bank Mydt Khandwa ","Jila Sahakari Kendriya Bank Maryadit Balaghat ","Jila Sahakari Kendriya Bank Maryadit Betul ","Jila Sahakari Kendriya Bank Maryadit Bhind ","Jila Sahakari Kendriya Bank Maryadit Chhindwara ","Jila Sahakari Kendriya Bank Maryadit Jagdalpur ","Jila Sahakari Kendriya Bank Maryadit Raipur ","Jila Sahakari Kendriya Bank Maryadit Rajgarh ","Jila Sahakari Kendriya Bank Maryadit Rajnandgaon ","Jila Sahakari Kendriya Bank Maryadit Sagar ","Jila Sahakari Kendriya Bank Maryadit Shivpuri ","Jila Sahakari Kendriya Bank Maryadit Sidhi ","Jila Sahakari Kendriya Bank Mydt Ambikapur ","Jila Sahakari Kendriya Bank Mydt Chhatarpur ","Jila Sahakari Kendriya Bank Mydt Dhar ","Jila Sahakari Kendriya Bank Mydt Durg ","Jila Sahakari Kendriya Bank Mydt Guna ","Jila Sahakari Kendriya Bank Mydt Hoshangabad ","Jila Sahakari Kendriya Bank Mydt Mandla ","Jila Sahakari Kendriya Bank Mydt Mandsaur ","Jila Sahakari Kendriya Bank Mydt Morena ","Jila Sahakari Kendriya Bank Mydt Narsinghpur ","Jila Sahakari Kendriya Bank Mydt Panna ","Jila Sahakari Kendriya Bank Mydt Ratlam ","Jila Sahakari Kendriya Bank Mydt Rewa ","Jila Sahakari Kendriya Bank Mydt Satna ","Jila Sahakari Kendriya Bank Mydt Sehore ","Jila Sahakari Kendriya Bank Mydt Shahdol ","Jila Sahakari Kendriya Bank Mydt Shajapur ","Jila Sahakari Kendriya Bank Mydt Tikamgarh ","Jila Sahakari Kendriya Bank Mydt Ujjain ","Jila Sahakari Kendriya Bank Mydt Vidisha ","Jila Sahakari Kendriya Bank Mydt. Jabalpur ","Jila Sahakari Kendriya Bank Mydtt Dewas ","Jila Sahakari Kendriya Bank Myt Seoni ","Jila Sahkari Kendriya Bank Maryadit Bilaspur ","Jila Sahkari Kendriya Bank Maryadit Khargone ","Jila Sahkari Kendriya Bank Mydt Damoh ","Jila Sahkari Kendriya Bank Mydt Datia ","Jila Sahkari Kendriya Bank Mydt Jhabua ","Jilla Sahakari Kendriya Bank Mydt Raisen ","Jind Central Co-operative Bank ","Jio Payments Bank ","Jivaji Sahakari Bank  Ichalkaranji ","Jivan Commercial Co-operative Bank ","Jodhpur Central Co-operative Bank ","Jodhpur Nagrik Sahakari Bank ","Jogindra Central Co-operative Bank ","Jowai Co-operative Urban Bank ","JP Morgan Chase Bank NA ","Junagadh Commercial Co-operative Bank ","Junagadh Jilla Sahakari Bank ","Kachchh District Central Co-operative Bank ","Kadappa District Co-operative Central Bank ","Kaduthuruthy Urban Co-operative Bank ","Kagal Co-operative Bank Kagal ","Kaira Dist Central Co-operative Bank ","Kaithal Central Co-operative Bank ","Kakatiya Co-operative Urban Bank ","Kalaburagi and Yadgir Dist Co-operative Central Bank ","Kallappanna Awade Ichalkaranji Janata Sahakari Bank ","Kalna Town Credit Co-operative Bank ","Kalol Nagarik Sahakari Bank ","Kalupur Commercial Co-operative Bank ","Kalyan Janata Sahakari Bank ","Kamala Co-operative Bank Solapur ","Kanakamahalakshmi Co-operative Bank ","Kanara District Central Co-operative Bank ","Kancheepuram Central Co-operative Bank ","Kangra Central Co-operative Bank ","Kangra Co-operative Bank ","Kankaria Mainagar Nagrik Sahakari Bank ","Kannur Co-operative Urban Bank ","Kannur District Co-operative Bank ","Kanyakumari District Central Co-operative Bank ","Kapurthala Central Co-operative Bank ","Karad Urban Co-operative Bank ","Karamana Co-operative Urban Bank ","Karan Urban Co-operative Bank ","Karimnagar District Co-operative Central Bank ","Karjan Nagrik Sahakari Bank ","Karnal Central Co-operative Bank ","Karnatak Central Co-operative Bank Dharwad ","Karnataka Bank ","Karnataka Gramin Bank ","Karnataka Mahila Sahakari Bank ","Karnataka State Co-operative Apex Bank ","Karnataka Vikas Grameena Bank ","Karnavati Co-operative Bank ","Karur Vysya Bank ","Kasaragod Co-operative Town Bank No 970 ","Kasaragod District Co-operative Bank ","Kashipur Urban Co-operative Bank ","Kashmir Mercantile Co-operative Bank ","Katihar District Central Co-operative Bank ","Kattappana Urban Co-operative Bank ","Kavita Urban Co-operative Bank ","KEB Hana Bank ","Keonjhar Central Co-operative Bank ","Kerala Gramin Bank ","Kerala Mercantile Co-operative Bank ","Kerala State Co-operative Bank ","Keshav Sehkari Bank ","Khagaria District Central Co-operative Bank ","Khalilabad Nagar Sahkari Bank ","Khambhat Nagarik Sahakari Bank ","Khamgaon Urban Co-operative Bank ","Khardah Co-operative Bank ","Khattri Co-operative Urban Bank ","Kheda People's Co-operative Bank ","Khurda Central Co-operative Bank ","Kisan Nagari Sahakari Bank Maryadit Parbhani ","Kodagu District Co-operative Central Bank ","Kodinar Nagrik Sahakari Bank Limited,kodinar ","Kodinar Taluka Co-operative Banking Union K ","Kodoli Urban Co-operative Bank Kodoli ","Kodungallur Town Co-operative Bank ","Kohinoor Sahakari Bank Ichalkaranji ","Kokan Mercantile Co-operative Bank ","Kolar And Chickballapur Dt Co-operative Central Bank ","Kolhapur Dist.central Co-operative Bank Kolhapur ","Kolhapur Mahila Sahakari Bank ","Kolhapur Urban Co-operative Bank ","Kolkata Mahila Co-operative Bank ","Kolkata Police Co-operative Bank ","Kollam District Co-operative Bank ","Konark Urban Co-operative Bank ","Konoklota Mahila Urban Co-operative Bank ","Kookmin Bank ","Kopargaon Peoples Co-operative Bank ","Koraput Central Co-operative Bank ","Kosamba Mercantile Co-operative Bank ","Kota Central Co-operative Bank Kota ","Kota Mahila Nagrik Sahakari Bank ","Kota Nagrik Sahkari Bank Kota ","Kotak Mahindra Bank ","Koteshwara Sahakari Bank Niyamitha ","Kottakkal Co-operative Urban Bank ","Kottayam District Co-operative Bank ","Koylanchal Urban Co-operative Bank ","Kozhikode District Co-operatiave Bank ","Kozhikode District Co-operative Bank ","Kranthi Co-operative Urban Bank ","Krishna Bhima Samruddhi Local Area Bank ","Krishna District Co-operative Bank ","Krishna Mercantile Co-operative Bank ","Krishna Sahakari Bank Rethare Bk ","Krishnagar City Co-operative Bank ","Kukarwada Nagarik Sahakari Bank ","Kumbakonam Central Co-operative Bank ","Kumbhi Kasari Sahkari Bank Kuditre ","Kurla Nagarik Sahakari Bank ","Kurmanchal Nagar Sahkari Bank ","Kurukshetra Central Co-operative Bank ","Kutch Co-operative Bank ","Kuttiady Co-operative Urban Bank ","Lakhimpur Urban Co-operative Bank ","Lalbaug Co-operative Bank ","Latur District Central Co-operative Bank ","Latur Urban Co-operative Bank Latur ","Laxmi Co-operative Bank Solapur ","Laxmi Mahila Nagrik Sahakari Bank Maryadit ","Laxmi UrbanCo-operative Bank Latur ","Laxmi Vilas Bank ","Laxmibai Mahila Nagrik Sahakari Bank Maradit ","Lic Employees Co-operative Bank,udupi ","Liluah Co-operative Bank ","Lokmangal Co-operative Bank Solapur ","Loknete Dattaji Patil Sahakari Bank ","Lokvikas Nagari Sahakari Bank Aurangabad ","Lonavala Sahakari Bank ","Lucknow Urban Co-operative Bank ","Ludhiana Central Co-operative Bank ","Lunawada Nagarik Sahakari Bank ","Lunawada Peoples Co-operative Bank ","M.s.Co-operative Bank ","Madanapalle Co-operative Town Bank ","Madheshwari Urban Development Co-operative Bank ","Madhya Pradesh Rajya Sahakari Bank Maryadit ","Madhyanchal Gramin Bank ","Madurai District Central Co-operative Bank ","Magadh Central Co-operative Bank ","Mahabhairab Co-operative Urban Bank ","Mahanagar Co-operative Bank ","Mahanagar Co-operative Urban Bank ","Mahanagar Nagrik Sahakari Bank Maryadit ","Maharaja Co-operative Urban Bank ","Maharana Pratap Co-operative Urban Bank ","Maharashtra Gramin Bank ","Maharashtra State Co-operative Bank ","Mahatma Fule Urban Co-operative Bank,amravati ","Mahaveer Co-operative Bank ","Mahaveer Co-operative Urban Bank ","Mahendragarh Central Co-operative Bank ","Mahesh Sahakari Bank Pune ","Mahesh Urban Co-operative Bank ","Mahesh Urban Co-operative Bank Ahmedpur ","Mahesh Urban Co-operative Bank Solapur ","Mahesh Urbank Co-operative Bank Parli V. ","Mahila  Nagrik Sahakari Bank Maryadit Mahasamund ","Mahila Co-operative Nagarik Bank,bharuch ","Mahila Urban Co-operative Bank ","Mahoba Urban Co-operative Bank Mahoba ","Mahudha Nagarik Sahakari Bank ","Makarpura Industrial Estate Co-operative Bank ","Malad Sahakari Bank ","Malappuram District Co-operative Bank ","Malda District Central Co-operative Bank ","Malkapur Urban Co-operative Bank ","Malleshwaram Co-operative Bank ","Malviya Urban Co-operative Bank ","Mamasaheb Pawar Satyavijay Co-operative Bank ","Mandvi Mercantile Co-operative Bank ","Mandya District Co-operative Central Bank ","Mangaldai Nagar Samabai Bank ","Mangalore Catholic Co-operative Bank ","Mangalore Co-operative Town Bank ","Manipur Rural Bank ","Manipur State Co-operative Bank ","Manipur Womens Co-operative Bank ","Manjeri Co-operative Urban Bank ","Manmandir Co-operative Bank ","Mann Deshi Mahila Sahkari Bank ","Manorama Co-operative Bank Solapur ","Mansa Central Co-operative Bank ","Mansa Nagarik Sahakari Bank ","Mansarovar Urban Co-operative Bank ","Mansing Co-operative Bank ","Manvi Pattana Souharda Sahakari Bank Ni ","Maratha Co-operative Bank ","Marketyard Commercial Co-operativebank ","Mashreq Bank ","Mattancherry Mahajanik Co-operative Urban Bank ","Mattancherry Sarvajanik Co-operative Bank ","Mayani Urban Co-operative Bank ","Mayurbhanj Central Co-operative Bank ","Md Pawar Peoples Co-operative Bank Urun Islampur ","Meenachil East Urban Co-operative Bank ","Meghalaya Co-operative Apex Bank ","Meghalaya Rural Bank ","Meghraj Nagarik Sahakari Bank ","Mehmadabad Urban Peoples Co-operative Bank ","Mehsana District Central Co-operative Bank ","Mehsana Nagarik Sahakari Bank ","Mehsana Urban Co-operative Bank ","Merchants Liberal Co-operative Bank ","Merchants Souharda Sahakara Bank Niyamita ","Merchants Urban Coop Bank ","Midnapore Peoples Co-operative Bank ","Mizoram Co-operative Apex Bank ","Mizoram Rural Bank ","Mizoram Urban Co-operative Development Bank ","Mizuho Bank ","Modasa Nagarik Sahkari Bank ","Model Co-operative Bank ","Moga Central Co-operative Bank ","Mogaveera Co-operative Bank ","Moirang Primary Co-operative Bank ","Monghyr Jamui Central Co-operative Bank ","Motihari Central Co-operative Bank ","MUFG Bank ","Mugberia Central Co-operative Bank ","Muktsar Central Co-operativeerated Bank ","Mumbai District Central Co-operative Bank ","Municipal Co-operative Bank ","Murshidabad District Central Co-operative Bank ","Muslim Co-operative Bank ","Muzaffarnagar District Co-operative Bank ","Muzaffarpur Central Co-operative Bank ","Mysore Chamarajanagar District Co-operative Bank ","Mysore Silk Cloth Merchants Co-Operative Bank ","Nabadwip Co-operative Credit Bank ","Nadapuram Co-operative Bank ","Nadia District Central Co-operative Bank ","Nadiad Peoples Co-operative Bank ","Nagaland Rural Bank ","Nagaland State Co-operative Bank ","Nagar Sahakari Bank Gorakhpur ","Nagar Sahkari Bank ","Nagar Urban Co-operative Bank ","Nagar Vikas Sahkari Bank ","Nagarik Sahakari Bank Bhiwandi ","Nagarik Sahakari Bank Maryadit Durg ","Nagarik Sahakari Bank Maryadit,jagdalpur ","Nagaur Central Co-operative Bank ","Nagaur Urban Co-operative Bank ","Nagina Urban Co-operative Bank ","Nagnath Urban Co-operative Bank Hingoli ","Nagpur Nagarik Sahakari Bank ","Nagrik Sahakari Bank ","Nagrik Sahakari Bank Indore ","Nagrik Sahakari Bank Lucknow ","Nagrik Sahakari Bank Maryadit Gwalior ","Nagrik Sahakari Bank, Vidisha ","Nainital Bank ","Nainital District Co-operative Bank ","Nakodar Hindu Urban Co-operative Bank ","Nalanda Central Co-operative Bank Nalanda ","Nalbari Urban Co-operative Bank ","Nalgonda Dist. Co-operative Central Bank ","Nandani Sahakari Bank Nandani ","Nanded District Central Co-operative Bank ","Nanded Merchants Co-operative Bank Nanded ","Nandura Urban Co-operative Bank Nandura ","Narmada Jhabua Gramin Bank ","Naroda Nagrik Co-operative Bank ","Nashik District Girna Sahakari Bank( Under Rbi Direction) ","Nashik District Industrial & Mercantile Co-operative Bank ","Nashik Jillha Mahila Vikas Sahakari Bank ","Nashik Zilha Sarkari & Parishad Karmachari Sb Nmt ","Nasik District Central Co-operative Bank ","Nasik Jilha Mahila Sahakari Bank ","Nasik Merchants Co-operative Bank ","Nasik Road Deolali Vyapari Sahakari Bank ","National Bank for Agriculture and Development ","National Bank of Abu Dhabi PJSC ","National Central Co-operative Bank Bettiah ","National Co-operative Bank ","National Co-operative Bank Bangalore ","National Urban Co-operative Bank ","National Urban Co-operative Bank, Pratapgarh ","National Urban Co-operative Bank,bahraich ","Nav Jeevan Co-operative Bank ","Navabharat Co-operative Urban Bank ","Naval Dockyard Co-operative BANK ","Navanagara Urban Co-operative Bank ","Navi Mumbai Co-operative Bank ","Navnirman Co-operative Bank ","Nawada Central Co-operative Bank ","Nawanagar Co-operative Bank ","Nawanshahr Central Co-operative Bank ","Nayagarh District Central Co-operative Bank ","Neela Krishna Co-operative Urban Bank ","Nehrunagar Co-operative Bank ","New India Co-operative Bank ","New Urban Co-operative Bank Rampur ","Neyyattinkara Co-operative Urban Bank ","Nidhi Co-operative Bank ","Nilambur Co-operative Urban Bank ","Nileshwar Co-operative Urban Bank ","Nilgiris District Central Co-operative Bank ","Nilkanth Co-operative Bank ","Nirmal Urban Co-operative Bank Nagpur ","Nishigandha Sahakari Bankpandharpur ","NKGSB Co-operative Bank ","Noble Co-operative Bank ","Noida Commercial Co-operative Bak ","North East Small Finance Bank ","Northern Railway Multi State Primary Co-operative Bank ","NSDL Payments Bank ","Nutan Nagari Sahakari Bank Ichalkaranji","Nutan Nagarik Sahakari Bank","Odisha Gramya Bank","Odisha State Co-operative Bank","Ojhar Merchant's Co-operative Bank ","Omkar Nagreeya Sahkari Bank ","Oriental Bank of Commerce ","Osmanabad District Central Co-operative Bank","Osmanabad Janata Sahakari Bank","Ottapalam Co-operative Urban Bank","Pachora Peoples Co-operative Bankpachora","Padra Nagar Nagrik Sahakari Bank ","Palakkad District Co-operative Bank","Pali Central Co-operative Bank","Pali Urban Co-operative Bank","Pallavan Grama Bank","Palus Sahakari Bank","Panchkula Central Co-operative Bank","Panchkula Urban Co-operative Bank Lmited ","Panchmahal District Co-operative Bank","Panchsheel Mercantile Co-operative Bank","Pandharpur Urban Co Op. Bank Pandharpur","Pandharpur Urban Co-operative Bank","Panipat Urban Co-operative Bank","Paraspar Sahayak Co-operative Bank ","Parbhani District Central Co-operative Bank","Parshwanath Co-operative Bank","Parwanoo Urban Co-operative Bank","Paschim Banga Gramin Bank","Patan Co-operativeeartive Bank","Patan Nagarik Sahakari Bank ","Patan Urban Co-operative Bank Patan","Pathanmthitta District Co-operative Bank","Patiala Central Co-operative Bank","Patliputra Central Co-operative Bank","Pavana Sahakari Bank ","Payangadi Urban Co-operative Bank","Paytm Payments Bank","Payyoli Co-operative Urban Bank","Peoples' Co-operative Bank ","Peoples Urban Co-operative Bank ","Pimpalgaon Merchants Co-operative Bank","Pimpri Chinchwad Sahakari Bank Maryadit,pimpri","Pithoragarh Zila Sahkari Bank","Pochampally Co-operative Urban Bank","Pondicherry State Co-operative Bank ","Poornawadi Nagrik Sahakari Bank","Pragathi Krishna Gramin Bank","Pragati Co-operative Bank,thara","Pragati Mahila Nagrik Sahakari Bank Bhilai","Pragati Sahakari Bank","Prakasam District Co-operative Central Bank ","Pratap Co-operative Bank","Prathamik Shikshak Sahakari Bank","Pravara Sahakari Bank","Prerana Co-operative Bank","Prime Co-operative Bank","Priyadarshani Nagari Sahakari Bank Jalna.","Proddatur Co-operative Town Bank ","Progressive Co-operative Bank","Progressive Mercantile Co-operative Bank","Progressive Urban Co-operative Bank","PT Bank Maybank Indonesia TBK","Pudukottai District Central Co-operative Bank","Puduvai Bharathiar Grama Bank ","Pune Cantonment Sahakari Bank","Pune District Central Co-operative Bank","Pune Merchant's Co-operative Bank ","Pune People's Co-operative Bank","Pune Urban Co-operative Bank ","Punjab & Sind Bank","Punjab Gramin Bank","Punjab National Bank","Punjab State Cooperative Bank","Punjab State Co-operative Bank","Puri Urban Co-operative Bank","Purnea District Central Co-operative Bank ","Purulia Central Co-operative BANK","Purvanchal Co-operative Bank Gazipur","Purvanchal Gramin Bank","Pusad Urban Cooperative Bank","Pusad Urban Co-operative Bank","Qatar National Bank ","Quilon Co-operative Urban Bank","Rabobank International","Radhasoami Urban Co-operative Bank","Raichur District Central Co-operative Bank","Raigad District Central Co-operative Bank","Raigad Sahakari Bank ","Raiganj Central Co-operative Bank","Railway Employees Co-operative Bank","Railway Employees Co-operative Banking Society","Raipur Urban Mercantile Co-operative Bank","Raj Laxmi Mahila Urban Co-operative Bank Jaipur ","Rajadhani Co-operati Urban Bank","Rajajinagar Co-operative Bank","Rajapur Urban Co-operative Bank","Rajarambapu Sahakari Bank","Rajarambapu Sahakari Bank Peth","Rajarshi Shahu Govt Servants Co-operative Bank Kolh ","Rajarshi Shahu Sahakari Bank","Rajasthan Marudhara Gramin Bank","Rajasthan State Co-operative Bank","Rajasthan Urban Co-operative Bank","Rajdhani Nagar Sahkari Bank","Rajgurunagar Sahakari Bank ","Rajkot Commercial Co-operative Bank","Rajkot Nagarik Sahakari Bank","Rajkot Peoples Co-operative Bank","Rajlaxmi Urban Co-operative Bank","Rajnandgaon District Central Co Operative Bank","Rajpipla Nagarik Sahakari Bank ","Rajputana Mahila Urban Co-operative Bak","Rajsamand Urban Co-operative Bank","Ramanathapuram District Central Co-operative Bank","Rameshwar Co-operative Bank","Ramgarhia Co-operative Bank ","Rampur Zila Sahkari Bank","Ramrajya Sahakari Bank","Rander Peoples Co-operative Bank","Ranga Reddy Co-operative Urban Bank","Rani Channamma Mahila Sahakari Bank","Raniganj Co-operative Bank ","Ranilaxmibai Urban Co-operative Bank","Ranuj Nagrik Sahakari Bank","Ratnagiri District Central Co-operative Bank","Ravi Commercial Urban Co-operative Bank","RBL Bank ","Rendal Sahakari Bank Rendal","Reserve Bank Employees Co-operative Bank","Reserve Bank Of India","Reserve Bank of India","Rewari Central Co-operative Bank","Rohika Central Co-operative Bank Madhubani ","Rohtak Central Co-operative Bank","Ropar Central Co-operative Bank","Royal Bank of Scotland N.V.","S S L S A Kurundwad Urban Bank","S.a.s Nagar Central Co-operative Bank","Sabarkantha District Central Co-operative Bank ","Sadguru Gahininath Urban Co-operative Bank Akluj","Sadguru Nagrik Sahakari Bank Maryadit","Sadhana Sahakari Bank","Sadhana Sahakari Bank Pune","Sahebrao Deshmukh Co-operative Bank","Sahyadri Sahakari Bank ","Saibaba Nagari Sahakari Bank","Salal Sarvodaya Nagarik Sahakari Bank","Salem District Central Co-operative Bank","Samarth Sahakari Bank","Samastipur District Central Co-operative Bank","Samata Co-operative Development Bank ","Samata Sahakari Bank","Sambalpur District Co-operative Central Bank","Sampada Sahakari Bank","Samruddhi Co-operative Bank","Sandur Pattana Souharda Sahakari Bank Niyamitha","Sangamner Merchants Co-operative Bank ","Sanghamitra Co-operative Urban Bank","Sangli District Central Co-operative Bank","Sangli Sahakari Bank","Sangli Urban Co-operative Bank","Sangrur Central Co-operative Bank","Sankheda Nagarik Sahakari Bank ","Sanmati Sahakari Bank","Sanmitra Sahakari Bank","Sanmitra Urban Co-operative Bank","Sant Sopankaka Sahakari Bank","Santragachi Co-operative Bank","Saptagiri Grameena Bank ","Sarakari Naukarara Sahakari Bank Niyamt Vijayapura","Sarangpur Co-operative Bank","Saraspur Nagarik Co-operative Bank","Saraspur Nagrik Co-operative Bank","Saraswat Co-operative Bank ","Saraswati Sahakari Bank","Sardar Bhiladwala Pardi People's Co-operative Bank ","Sardar Vallabhbhai Sahakari Bank ","Sardarganj Mercantile Co-operative Bank ","Sardargunj Mercantile Co-operative Bank Patan ","Sarjeraodada Naik Shirala Sahakari Bank","Sarva Haryana Gramin Bank","Sarva Up Gramin Bank","Sarvodaya Commerical Co-operative Bank","Sarvodaya Co-operative Bank Mumbai","Sarvodaya Nagrik Sahkari Bank ","Sarvodaya Sahakari Bank","Sasaram Bhabhua Central Co-operative Bank","Satara District Central Co-operative Bank","Satara Sahakari Bank Ltd","Satara Shakari Bank","Sathamba Peoples Co-operative Bank ","Saurashtra Co-operative Bank","Saurashtra Gramin Bank","Sawai Madhopur Kendriya Sahakari Bank","Sawai Madhopur Urban Co-operative Bank","Sawantwadi Urban Co-operative Ban","SBER Bank ","SBM Bank","Secunderabad Co-operative Urban Bank","Secunderabad Mercantile Co-operative Urban Bank","Sehore Nagrik Sahakari Bank Sehor","Seva Vikas Co-operative Bank","Sevalia Urban Co-operative Bank ","Shahada Peoples Co-operative Bank","Shamrao Vithal Co-operative Bank","Shankar Nagari Sahakari Bank","Shankarrao Mohite Patil Sahakri Bank","Sharad Nagari Sahakari Bank","Sharad Sahakari Bank ","Shiggaon Urban Co-operative Bank","Shihori Nagarik Sahakari Bank","Shikshak Sahakari Bank","Shillong Co-operative Urban Bank","Shimla Urban Co-operative Bank","Shimoga Arecanut Mandy Merchants Co-Operative Bank ","Shimoga District Co-operative Central Bank","Shinhan Bank","Shirpur Peoples Co-operative Bank","Shivaji Nagari Sahakari Bank","Shivalik Mercantile Co-operative Bank","Shivdaulat Sahakari Bank","Shoranur Co-operative Urban Bank ","Shree Balaji Urban Co-operative Bank","Shree Basaveshwar Co-operative Bank","Shree Basaveshwar Urban Coop Bank","Shree Bharat Co-operative Bank","Shree Bhavnagar Nagrik Sahakari Bank","Shree Botad Mercantile Co-operative Bank ","Shree Co-operative Bank","Shree Dharati Co-operative Bank","Shree Kadi Nagarik Sahakari Bank","Shree Laxmi Co-operative Bank","Shree Mahalaxmi Mercantile Co-operative Bank","Shree Mahalaxmi Urban Co-operative Credit Bank ","Shree Mahavir Sahakari Bank","Shree Mahesh Co-operative Bank Nashik","Shree Mahuva Nagrik Sahakari Bank","Shree Panchganga Nagari Sahakari Bank","Shree Parswanath Co-operative Bank","Shree Samarth Sahakari Bank Nashik ","Shree Sharada Sahakari Bank","Shree Thyagaraja Co-operative Bank","Shree Vardhaman Sahakari Bank","Shree Veershaiv Co-operative Bank","Shree Warana Sahakari Bank","Shreeji Bhatia Co-operative Bank ","Shri Adinath Co-operative Bank","Shri Arihant Co-operative Bank","Shri Basaveshwar Sahakari Bank Nyt.bagalkot","Shri Bharat Urban Co-operative Bank Jaysingpur","Shri Chhani Nagrik Sahkari Bank ","Shri Chhatrapati Rajashri Shahu Urban Co-operative Bank","Shri D T Patil Co-operative Bank","Shri Ganesh Sahakari Bank","Shri Kanyaka Nagari Sahakari Bank","Shri Mahalaxmi Co-operative Bak Kolhapur ","Shri Mahavir Urban Co-operative Bank","Shri Mahila Sewa Sahakari Bank","Shri Rajkot District Co-operative Bank","Shri Rukmini Sahakari Bank","Shri Veershaiv Co-operative Bank","Shri Vijay Mahantesh Co-operative Bank ","Shri Vinayak Sahakari Bank","Shrikrishna Co-operative Bank","Shrimant Malojiraje Sahakari Bank","Shripatraodada Sahakari Bank","Shriram Urban Co-operative Bank","Shubhalakshmi Mahila Co-operative Bank ","Shushruti Souharda Sahakara Bank Niyamita","Siddheshwar Urban Co-operative Bank Maryadit Sillod","Siddhi Co-operative Bank","Sihor Mercantile Co-operative Bank","Sihor Nagarik Sahakari Bank ","Sikar Kendriya Sahakari Bank","Sikkim State Co-operative Bank","Sind Co-operative Urban Bank","Sindhudurg  District Central Co-operative Bank","Sindhudurg District Central Co-operative Bank","Sir M Visvesvaraya Co-operative Bank ","Sircilla Co-operative Urban Bank","Sirohi Central Co-operative Bank","Sirsa Central Co-operative Bank","Sirsi Urban Sahakari Bank","Sitamarhi Central Co-operative Bank","Sivagangai District Central Co-operative Bank ","Siwan Central Co-operative Bank","Smriti Nagrik Sahakari Bank","Social Co-operative Bank","Societe Generale","Solapur District Central Co-operative Bank","Solapur Janata Sahakari Bank ","Solapur Siddheshwar Sahakari Bank","Solapur Social Urban Co-operative Bank","Sonali Bank","Sonbhadra Nagar Sahkari Bank","Sonepat Central Co-operative Bank","Sonepat Urban Co-operative Bank ","Soubhagya Mahila Souhardha Sahakari Bank","South Canara District Central Co-operative Bank","South Indian Bank","Sree Charan Souhardha Co-operative Bank","Sree Narayana Guru Co-operative Bank ","Sreenidhi Souharda Sahakari Bank Niyamitha","Sreenivasa Padmavathi Co-operative Bank","Sri Banashankari Mahila Co-operative Bank","Sri Basaveshwar Pattana Sahakari Bank","Sri Channabasavaswamy Souhardha Pattana Sahak Bank ","Sri Kannikaparameswari Co-operative Bank","Sri Potti Sriramulu Nellore Dccb","Sri Sudha Co-operative Bank","Sri Vasavamba Co-operative Bank","Standard Chartered Bank","State Bank of India ","State Transport Bank Mumbai Central","State Transport Co-operative Bank","Sterling Urban Co-operative Bank","Suco Souharda Sahakari Bank","Sudha Co-operative Urban Bank","Sulaimani Co-operative Bank ","Sultan's Battery Co-operative Urban Bank ","Sumerpur Mercantile Urban Co-operative Bank ","Sumitomo Mitsui Banking Corporation ","Sundargarh District Central Co-operative Bank ","Sundarlal Sawaji Urban Co-operative Bank ","Surat District Co-operative Bank","Surat Mercantile Co-operative Bank","Surat National Co-operative Bank","Surat People's Co-operative Bank ","Surendranagar District Co-operative Bank ","Suryoday Small Finance Bank ","Sutex Co-operative Bank","Suvarnayug Sahakari Bank","Syndicate Bank","Tamilnadu Industrial Co-operative Bank","Tamilnadu Mercantile Bank","Tamilnadu State Apex Co-operative Bank","Tamluk-ghatal Central Co-operative Bank ","Tapindu Urban Co-operative Bank","Tarn Taran Central Co-operative Bank","Tasgaon Urban Co-operative Banktasgaon","Tehri Garhwal Zila Sahkari Bank","Telangana Grameena Bank ","Telangana State Co-operative Apex Bank","Textile Co-operative Bank of Surat","Textile Manufacturers Co-operative Bank","Textile Traders Co-operative Bank","Thane Bharat Sahakari Bank ","Thane District Central Co-operative Bank","Thane Janata Sahakari Bank","Thanjavur Central Co-operative Bank","The Malad Sahakari Bank Ltd","The Union Co-operative Bank Mahinagar","The Vijay Co-operative Bank ","Thiruvananthapuram District Co-operative Bank","Thiruvannamalai District Central Co-operative Bank","Thoothukudi District Central Co-operative Bank","Thrissur District Co-operative Bank ","Tiruchirapalli Dist. Cent Co-operative Bank","Tirunelveli District Central Co-operative Bank","Tirupati Urban Co-operative Bank","Tirur Urban Co-operative Bank","Town Co-operative Bank Hoskote ","Transport Co-operative Bank","Trichur Urban Co-operative Bank","Tripura Gramin Bank","Tripura State Co-operative Bank","Tumkur District Central Bank","Tumkur Grain Merchant's Co-operativeerate Bank ","Tura Urban Co-operative Bank ","UCO Bank","Udaipur Central Co-operative Bank","Udaipur Mahila Samridhi Urban Co-operative Bank","Udaipur Mahila Urban Co-operative Bank","Udaipur Urban Co-operative Bank","Udham Singh Nagar District Co-operative Bank ","Ujjivan Small Finance Bank","Uma Co-operative Bank","Umiya Urban Co-operative Bank","Umreth Urban Co-operative Bank","Una Peoples Co-operative Bank","Unava Nagrik Sahakari Bank ","Union Bank of India","Union Co-operative Bank","United Bank of India","United Co-operative Bank","United India Co-operative Bank","United Mercantile Co-operative Bank","United Overseas Bank ","United Puri Nimapara Central Bank","Universal Co-operative Urban Bank","Unjha Nagarik Sahakari Bank","Urban Co Operativa Bank Siddharthnagar","Urban Co-operative Bank Bareilly","Urban Co-operative Bank Basti ","Urban Co-operative Bank Budaun","Urban Co-operative Bank Dehradun","Urban Co-operative Bank Dharangaon","Urban Co-operative Bank Mainpuri","Urban Co-operative Bank No 1758 Perinthalmanna","Urban Co-operative Bank Perinthalmanna ","Urban Co-operative Bank Rourkela","Urban Co-operative Bank Saharanpur","Utkal Grameen Bank","Utkarsh Small Finance Bank","Uttar Bihar Gramin Bank","Uttar Pradesh Co-operative Bank ","Uttarakhand Gramin Bank","Uttarakhand State Co-operative Bank","Uttarbanga Kshetriya Gramin Bank","Uttarkashi Zila Sahkari Bank","Uttarpara Co-operative Bank","Uttrakhand Co-operative Bank ","Vaidyanath Urban Co-operative Bank","Vaijapur Merchants Co-operative Bank","Vaish Co-operative Adarsh Bank","Vaish Co-operative New Bank","Vaishali District Central Co-operative Bank","Vaishali Shahari Vikas Co-op Bank ","Vaishya Nagari Sahakari Bank","Vaishya Sahakari Bank Mumbai","Vallabh Vidyanagar Commercial Bank","Valmiki Urban Co-operative Bank,pathri","Valsad District Central Co-operative Bank","Vananchal Gramin Bank ","Varachha Co-operative Bank","Vardhaman (mahila) Co-operative Urban Bank","Vardhaman Co-operative Bank","Vasai Janata Sahakari Bank","Vasai Vikas Sahakari Bank","Veerashaiva Sahakari Bank ","Vellore District Central Co-operative Bank","Veraval Mercantile Co-operative Bank","Veraval Peoples Co-operative Bank","Vidarbha Merchants Urban Co-operative Bank","Vidharbha Kokan Gramin Bank ","Vidya Sahakari Bank","Vidyanand Co-operative Bank","Vidyasagar Central Co-operative Bank","Vijay Commercial Co-operative Bank","Vijay Co-operative Bank","Vijaya Bank ","Vikas Sahakari Bank Limited Solapur","Vikas Souharda Co-operative Bank","Vikramaditya Nagrik Sahakari Bank","Villupuram District Central Co-operative Bank","Vima Kamgar Co-operative Bank ","Viramgam Mercantile Co-operative Bank ","Virudhunagar District Central Co-operative Bank","Visakhapatnam Co-operative Bank","Vishwas Co-operative Bank","Vishweshwar Sahakari Bank","Vita Merchants Co-operative Bank","Vita Urban Co-operative Bank ","Vivekanand Nagrik Sahkari Bank Mydt","Vyapari Sahakari Bank Maryadit Solapur","Vyaparik Audhyogik Sahakari Bank Ktd","Vyavsayak Sahkari Bank","Waghodia Urban Co-operative Bank","Wai Urban Co-operative Bank ","Wana Nagirik Sahakari Bank","Warangal District Co-operativeerative Central Bank","Warangal Urban Co-operativeerative Bank","Wardha Nagri Bank","Wardha Zilla Parishad Emp Urban Co-operative Bank","Wardhaman Urban Co-operative Bank Nagpur ","Washim Urban Co-operative Bank.","Wayanad District Co-operative Bank","West Bengal State Co-operative Bank","Women's Co-operative Bank ","Woori Bank ","Yadagiri Lakshmi Narsimha Swamy Co-operative Urban Bank ","Yadrav Co-operative Bank","Yamuna Nagar Central Co-operative Bank","Yashwant Co-operative Bank","Yavatmal District Central Co-operative Bank","Yavatmal Mahila Sahakari Bank","Yavatmal Urban Co-operative Bank ","Yes Bank","Yeshwant Nagari Sahakari Bank","Zila Sahakari Bank Lucknow","Zila Sahkari Bank Bareilly","Zila Sahkari Bank Bijnor","Zila Sahkari Bank Bulandshahar","Zila Sahkari Bank Garhwal Kotdwar ","Zila Sahkari Bank Ghaziabad","Zila Sahkari Bank Gorakhpur","Zila Sahkari Bank Haridwar","Zila Sahkari Bank Jhansi","Zila Sahkari Bank Kanpur","Zila Sahkari Bank Lakhimpur Kheri","Zila Sahkari Bank Lalitpur ","Zila Sahkari Bank Mathura","Zila Sahkari Bank Mau","Zila Sahkari Bank Meerut","Zila Sahkari Bank Mirzapur","Zila Sahkari Bank Moradabad","Zila Sahkari Bank Unnao","Zoroastrian Co-operative Bank" };
private String[] STORED_STATE_LIST = {"- - - - - Select State - - - - -","ANDHRA PRADESH","ARUNACHAL PRADESH", "ASSAM", "BIHAR", "CHANDIGARH", "CHHATTISGARH", "GOA", "GUJARAT", "HARYANA", "HIMACHAL PRADESH", "JAMMU AND KASHMIR", "JHARKHAND", "KARNATAKA", "KERALA","LAKSHADWEEP", "MADHYA PRADESH", "MAHARASHTRA", "MANIPUR", "MEGHALAYA", "MIZORAM", "NAGALAND", "ODISHA", "PUDUCHERRY", "PUNJAB", "RAJASTHAN", "SIKKIM", "TAMIL NADU", "TELANGANA", "TRIPURA", "UTTAR PRADESH", "UTTARAKHAND", "WEST BENGAL"};
    private String[] STORED_DIST_LIST = {"- - - - - Select District - - - -"};
    private String[] STORED_CITY_LIST = {"- - - - - Select city - - - -"};
    private String[] STORED_BRANCH_LIST = {"- - - - - Select Branch - - - -"};
    private String myBank;
    private String state;
    private String city;
    private String district;
    private String branch;
    private String URL_DISTRICT;
    private String URL_CITY;
    private String URL_BRANCH;
    private ArrayList<String> distList;
    private ArrayList<String> cityList;
    private ArrayList<String> branchList;
    private Button mFind;
    private TextView mResultIfsc;
    private CardView mCardIfsc;
    private ImageButton mClipb;
    private LinearLayout mRoot;
    private Button bt;
    private ClipboardManager myClipboard;
    private ClipData myClip;
    private String ifsc;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt = (Button) findViewById(R.id.button);
        distList = new ArrayList<>();
        cityList = new ArrayList<>();
        branchList = new ArrayList<>();
        mFind =findViewById(R.id.btnFindIfsc);
        mResultIfsc =  findViewById(R.id.tvResultIfsc);
        mCardIfsc =findViewById(R.id.cvIfsc);
        mClipb =  findViewById(R.id.ibClipboard);
        mRoot = findViewById(R.id.activity_main);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading Details \n\n Please wait...");

        myClipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);

        spinnerInflater();

    }



    @Override
    protected void onStart() {
        super.onStart();

        mFind.setOnClickListener(v -> {
            if (myBank=="- - - - - Select Bank - - - - -" || state == "- - - - - Select State - - - - -" || district == "- - - - - Select District - - - -" || city == "- - - - - Select city - - - -" || branch =="- - - - - Select Branch - - - -")
            {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Please Fill The Details ", Toast.LENGTH_SHORT).show();
            }
            else {
            getIfscCode();

            mCardIfsc.setVisibility(View.VISIBLE);

            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }});

        mClipb.setOnClickListener(v -> {

            String text = mResultIfsc.getText().toString();
            myClip = ClipData.newPlainText("text", text);
            myClipboard.setPrimaryClip(myClip);

            Snackbar snackbar = Snackbar
                    .make(mRoot, "IFSC copied to Clipboard", Snackbar.LENGTH_LONG);
            snackbar.show();
        });
    }

    public void spinnerInflater(){
        Spinner spinnerBank = (Spinner) findViewById(R.id.spBank);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, STORED_BANK_LIST);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBank.setAdapter(spinnerArrayAdapter);
        spinnerArrayAdapter.notifyDataSetChanged();
        spinnerBank.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                myBank = adapterView.getItemAtPosition(i).toString().trim();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(MainActivity.this,"Please fill the details",Toast.LENGTH_SHORT).show();

            }
        });


        Spinner spinnerState = (Spinner) findViewById(R.id.spState);

        ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, STORED_STATE_LIST);
        spinnerArrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerState.setAdapter(spinnerArrayAdapter2);
        spinnerArrayAdapter2.notifyDataSetChanged();
        spinnerState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                state = adapterView.getItemAtPosition(i).toString().trim();
                if(!state.equalsIgnoreCase("- - - - - Select State - - - - -"))
                getdistrict();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(MainActivity.this,"Please fill the details",Toast.LENGTH_SHORT).show();

            }
        });


        Spinner spinnerDistrict = (Spinner) findViewById(R.id.spDistrict);
        ArrayAdapter<String> spinnerArrayAdapter3 = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, STORED_DIST_LIST);
        spinnerArrayAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDistrict.setAdapter(spinnerArrayAdapter3);
        spinnerArrayAdapter3.notifyDataSetChanged();
        spinnerDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                district = adapterView.getItemAtPosition(i).toString();
                if(!district.equalsIgnoreCase("- - - - - Select District - - - -"))
                    getCity();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(MainActivity.this,"Please fill the details",Toast.LENGTH_SHORT).show();

            }
        });


        Spinner spinnerCity = (Spinner) findViewById(R.id.spCity);
        ArrayAdapter<String> spinnerArrayAdapter5 = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, STORED_CITY_LIST);
        spinnerArrayAdapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCity.setAdapter(spinnerArrayAdapter5);
        spinnerArrayAdapter5.notifyDataSetChanged();
        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                city = adapterView.getItemAtPosition(i).toString();
                if(!city.equalsIgnoreCase("- - - - - Select city - - - -"))
                    getBranch();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(MainActivity.this,"Please fill the details",Toast.LENGTH_SHORT).show();

            }
        });


        Spinner spinnerBranch = (Spinner) findViewById(R.id.spBranch);
        ArrayAdapter<String> spinnerArrayAdapter4 = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, STORED_BRANCH_LIST);
        spinnerArrayAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBranch.setAdapter(spinnerArrayAdapter4);
        spinnerBranch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                branch = adapterView.getItemAtPosition(i).toString();
                if(!branch.equalsIgnoreCase("- - - - - Select Branch - - - -"))
                    getIfscCode();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(MainActivity.this,"Please fill the details",Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void getdistrict() {
        progressDialog.show();
        URL_DISTRICT = "https://ifsc1-database.herokuapp.com/district/";
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        StringRequest objectRequest = new StringRequest(Request.Method.POST, URL_DISTRICT, response -> {
            try {
                JSONArray res = new JSONArray(response);
                for(int i=0;i<res.length();i++)
                {
                    JSONObject dist = res.getJSONObject(i);
                    String district = dist.getString("DISTRICT");
                    distList.add(district);
                }
                if(distList.size() == 0)
                {
                    Toast.makeText(MainActivity.this,"Please select a valid state",Toast.LENGTH_LONG).show();
                    distList.clear();
                    progressDialog.dismiss();
                }
                else {

                    String[] distArr = new String[distList.size()];
                    distArr = distList.toArray(distArr);
                    distList.clear();
                    List<String> list = Arrays.asList(distArr);
                    Set<String> set = new HashSet<>(list);

                    String[] result = new String[set.size()];

                    set.toArray(result);
                    Arrays.sort(result);
                    progressDialog.dismiss();
                    inflateDistSpinner(result);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_LONG).show()){
            @NonNull
            protected Map<String, String> getParams() {
                Map<String, String> para = new HashMap<String, String>();

                para.put("BankName",myBank);
                para.put("State", state);
                return para;
            }
        };
        objectRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(objectRequest);
    }

    public void inflateDistSpinner(String[] result) {
        Spinner spinnerDistrict = (Spinner) findViewById(R.id.spDistrict);

        ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, result);
        spinnerArrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDistrict.setAdapter(spinnerArrayAdapter2);
    }
    public void getCity()
    {
        progressDialog.show();
        URL_CITY = "https://ifsc1-database.herokuapp.com/city/";
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        StringRequest objectRequest = new StringRequest(Request.Method.POST, URL_CITY, response -> {
            try {

                JSONArray res = new JSONArray(response);
                for(int i=0;i<res.length();i++)
                {
                    JSONObject dist = res.getJSONObject(i);
                    String city = dist.getString("CITY");
                    cityList.add(city);
                }
                String[] cityArr = new String[cityList.size()];
                cityArr = cityList.toArray(cityArr);
                cityList.clear();
                List<String> list = Arrays.asList(cityArr);
                Set<String> set = new HashSet<>(list);

                String[] result = new String[set.size()];
                set.toArray(result);
                Arrays.sort(result);
                progressDialog.dismiss();

                inflateCitySpinner(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_LONG).show()){
            @NonNull
            protected Map<String, String> getParams() {
                Map<String, String> para = new HashMap<String, String>();

                para.put("BankName",myBank);
                para.put("State", state);
                para.put("District",district);
                return para;
            }
        };
        objectRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(objectRequest);
    }

    public void inflateCitySpinner(String[] result) {
        Spinner spinnerDistrict = (Spinner) findViewById(R.id.spCity);

        ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, result);
        spinnerArrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDistrict.setAdapter(spinnerArrayAdapter2);
    }
    public void getBranch()
    {
        progressDialog.show();
        URL_BRANCH = "https://ifsc1-database.herokuapp.com/branch/";
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        StringRequest objectRequest = new StringRequest(Request.Method.POST, URL_BRANCH, response -> {
            try {
                JSONArray res = new JSONArray(response);
                for(int i=0;i<res.length();i++)
                {
                    JSONObject dist = res.getJSONObject(i);
                    String branch = dist.getString("BRANCH");
                    branchList.add(branch);
                }
                String[] branchArr = new String[branchList.size()];
                branchArr = branchList.toArray(branchArr);
                branchList.clear();
                List<String> list = Arrays.asList(branchArr);
                Set<String> set = new HashSet<>(list);

                String[] result = new String[set.size()];
                set.toArray(result);
                Arrays.sort(result);
                progressDialog.dismiss();
                inflateBranchSpinner(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_LONG).show()){
            @NonNull
            protected Map<String, String> getParams() {
                Map<String, String> para = new HashMap<String, String>();

                para.put("BankName",myBank);
                para.put("State", state);
                para.put("District",district);
                para.put("City",city);
                return para;
            }
        };
        queue.add(objectRequest);
    }

    public void inflateBranchSpinner(String[] result) {
        Spinner spinnerDistrict = (Spinner) findViewById(R.id.spBranch);

        ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, result);
        spinnerArrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDistrict.setAdapter(spinnerArrayAdapter2);
    }
    public void getIfscCode() {
        progressDialog.show();
        String url = "https://ifsc1-database.herokuapp.com/ifsc/";
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        StringRequest objectRequest = new StringRequest(Request.Method.POST, url, response -> {
            try {


                JSONArray res = new JSONArray(response);
                progressDialog.dismiss();
                JSONObject dist = res.getJSONObject(0);
                String ifsc = dist.getString("IFSC");

                mResultIfsc.setText(ifsc);
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }

        }, error -> Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_LONG).show()){
            @NonNull
            protected Map<String, String> getParams() {
                Map<String, String> para = new HashMap<String, String>();

                para.put("BankName",myBank);
                para.put("State", state);
                para.put("District",district);
                para.put("City",city);
                para.put("Branch",branch);
                return para;
            }
        };
        objectRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(objectRequest);
        bt.setVisibility(View.VISIBLE);
        bt.setOnClickListener(view -> {
            Intent myIntent = new Intent(Intent.ACTION_SEND);
            myIntent.setType("text/plain");
            String body = "Bank Name :"+" "+myBank+"\n"+"Branch : "+branch+"\n"+"IFSC Code :"+(String) mResultIfsc.getText();
            String sub = "Your IFSC Code ";
            myIntent.putExtra(Intent.EXTRA_SUBJECT,sub);
            myIntent.putExtra(Intent.EXTRA_TEXT,body);
            startActivity(Intent.createChooser(myIntent, "Share Using"));
        });

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {

            progressDialog.dismiss();
            progressDialog = null;

    }
}
