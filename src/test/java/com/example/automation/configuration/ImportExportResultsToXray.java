package com.example.automation.configuration;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Test;

import javax.net.ssl.SSLContext;

import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.core.ZipFile;


public class ImportExportResultsToXray {

    String clientID = "93B37FB647824B09A6FD0C59815625CC";
    String clientSecret = "6487d186161b48ea2906cb415dc22b14b4f1d16602b36eb8b9cf9b0dcd680d55";
    static String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZW5hbnQiOiJiNmNhZGQwNS1lMzQxLTNmMTctYjU1Zi00OTM0MTI4MWQ4MmEiLCJhY2NvdW50SWQiOiI1ZWU0ZjM3YjFmYjJjYTBhYzU0OTNhMjMiLCJpc1hlYSI6ZmFsc2UsImlhdCI6MTc3MDI4NDA0NCwiZXhwIjoxNzcwMzcwNDQ0LCJhdWQiOiI5M0IzN0ZCNjQ3ODI0QjA5QTZGRDBDNTk4MTU2MjVDQyIsImlzcyI6ImNvbS54cGFuZGl0LnBsdWdpbnMueHJheSIsInN1YiI6IjkzQjM3RkI2NDc4MjRCMDlBNkZEMEM1OTgxNTYyNUNDIn0.7a2_KvkGTuLuXhDtbQ1H4beIAhXPbsav4PTfn_XrOtA";

    public String getToken() throws IOException, NoSuchAlgorithmException, KeyManagementException {
        // Méthode correcte pour HttpClient 4.x
        SSLContext sslContext = SSLContexts.custom()
                .useProtocol("TLSv1.2")  // useProtocol, PAS setProtocol
                .build();

        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(
                sslContext,
                new String[]{"TLSv1.2", "TLSv1.3"},
                null,
                SSLConnectionSocketFactory.getDefaultHostnameVerifier()
        );

        try (CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(sslConnectionSocketFactory)
                .build()) {

            HttpPost request = new HttpPost("https://xray.cloud.getxray.app/api/v1/authenticate");
            request.addHeader("Content-Type", "application/json");

            String input = "{ \"client_id\": \"" + clientID + "\", \"client_secret\": \"" + clientSecret + "\"}";
            request.setEntity(new StringEntity(input, StandardCharsets.UTF_8));

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                String result = EntityUtils.toString(response.getEntity());
                System.out.println(result);
                return result.replace("\"", "").trim();
            }
        }
    }

    @Test
    public void myTest() throws IOException, NoSuchAlgorithmException, KeyManagementException, KeyStoreException, InterruptedException {
        remonteeXray();
    }


    public static void remonteeXray () throws
        IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, InterruptedException {
            HttpURLConnection conn;
            URL url;
            String result;
            String URL = "https://xray.cloud.getxray.app/api/v1/import/execution/cucumber";
            //Call the openConnection method on the URL to create a connection object
            url = new URL(URL);
            conn = (HttpURLConnection) url.openConnection();


            //Various settings of HttpURLConnection
            //Set HTTP method to POST
            conn.setRequestMethod("POST");
            //Allow body submission of request
            conn.setDoInput(true);
            //Allow body reception of response
            conn.setDoOutput(true);
            //Specify Json format
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            conn.setRequestProperty("Authorization", "Bearer " + token);
// 2.Establish a connection
            conn.connect();
            // 3.Write to request and body
            //Get OutputStream from HttpURLConnection and write json string
            Thread.sleep(10000);
            PrintStream ps = new PrintStream(conn.getOutputStream());

            Path filePath = Path.of(System.getProperty("user.dir") + "\\target\\cucumber.json");
            String content = Files.readString(filePath);
            System.out.println("mon fichier  = " + content);
            ps.print(content);
            ps.close();

            // 4.Receive a response
            //HttpStatusCode 200 is returned at the end of normal operation
            if (conn.getResponseCode() != 200) {
                //Error handling
            }
            //Get InputStream from HttpURLConnection and read
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            result = sb.toString();
            // 5.Disconnect
            conn.disconnect();
            //Return the result to the caller
            System.out.println(result);
        }


    public static void downloadFeatureFiles(String token, String testKeys) {
        try {
            URL url = new URL("https://xray.cloud.getxray.app/api/v2/export/cucumber?keys=" + testKeys);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + token);
            conn.setRequestProperty("Content-Type", "application/json");

            InputStream inputStream = conn.getInputStream();
            FileOutputStream outputStream = new FileOutputStream("features.zip");
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.close();
            inputStream.close();
        } catch (Exception e) {
            System.err.println("Erreur lors du téléchargement des fichiers feature: " + e.getMessage());
        }
    }

    public static void unzip(){
        String source = "feature.zip";
        String destination = "C:\\Dev\\tp sauce demo\\selenium-cucumber-pom\\selenium-cucumber-pom\\src\\test\\resources\\features";

        try {
            ZipFile zipFile = new ZipFile(source);
            zipFile.extractAll(destination);
        }

        catch (ZipException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testUnzip() {
        unzip();


        File extractedFile = new File("C:\\Dev\\tp sauce demo\\selenium-cucumber-pom\\selenium-cucumber-pom\\src\\test\\resources\\features\\1_POEI2-654.feature");

        Assert.assertTrue("Le fichier feature n'a pas été extrait !", extractedFile.exists());
    }
}

