package prematest.config;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
	
	int count = 0;
	int maxlimit = 1;
	

	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		
	
		if(count<maxlimit) {
			
			count++;
			return true;
			
		}
		
		
		return false;
	}

}
