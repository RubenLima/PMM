package roberto.solobici;

/**
 *
 */
import android.app.Activity;
import android.os.Bundle;

import com.roberto.solobici.R;

public class AcercaDe extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //Hacemos visible la interfaz/layoutque se encuentra en acercade.xml
        setContentView(R.layout.acercade);
    }
}