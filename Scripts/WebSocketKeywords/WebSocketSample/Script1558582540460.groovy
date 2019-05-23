import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import java.net.URI;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository

import groovy.json.JsonSlurper as JsonSlurper

import websocketConnection.WebSocketConnect

//Sample server
String destUri = "ws://echo.websocket.org";


String msg ="This is test API request"
WebSocketClient client = new WebSocketClient();
socket = new WebSocketConnect(msg);
try
{
	client.start();

	URI echoUri = new URI(destUri);
	ClientUpgradeRequest request = new ClientUpgradeRequest();
	
	client.connect(socket,echoUri,request);
	System.out.printf("Connecting to : %s%n",echoUri);

	// wait for socket connection to close
	socket.awaitClose(5,TimeUnit.SECONDS);
	
	System.out.printf("Message has reached me : %s%n",socket.msgtoReturn);
		
}
catch (Throwable t)
{
	t.printStackTrace();
}
finally
{
	try
	{
		client.stop();
	}
	catch (Exception e)
	{
		e.printStackTrace();
	}
}
