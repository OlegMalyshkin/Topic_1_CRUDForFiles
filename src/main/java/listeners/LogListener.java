package listeners;

import org.apache.log4j.Logger;
import org.testng.*;

public class LogListener implements IInvokedMethodListener, ISuiteListener  {

    private static Logger log = Logger.getLogger( LogListener.class );

    @Override
    public void beforeInvocation( IInvokedMethod iInvokedMethod, ITestResult iTestResult ) {
        log.info( "About to begin executing " + returnMethodName(iInvokedMethod.getTestMethod()) );
    }

    @Override
    public void afterInvocation( IInvokedMethod iInvokedMethod, ITestResult iTestResult ) {
        log.info( "Completed executing " + returnMethodName(iInvokedMethod.getTestMethod()) );
    }

    private String returnMethodName( ITestNGMethod method) {
        return method.getRealClass().getSimpleName() + "." + method.getMethodName();

    }

    @Override
    public void onStart( ISuite iSuite ) {
        log.info("About to begin executing Suite " + iSuite.getName());
    }

    @Override
    public void onFinish( ISuite iSuite ) {
        log.info("About to end executing Suite " + iSuite.getName());
    }
}
