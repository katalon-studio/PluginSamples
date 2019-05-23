import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

//Send Login API
APIResponce = WS.sendRequest(findTestObject('Test API'))

//Check API responce
WS.verifyResponseStatusCode(APIResponce, 200)

//Get the Authorization Value
CustomKeywords.'com.plugin.keywords.token.accessToken.getAccessTokenFromAPI'('AuthorizationKey', APIResponce)

