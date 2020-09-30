// Generated code from Butter Knife. Do not modify!
package com.abajlo.abajlo.view;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.abajlo.abajlo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CUDFragment_ViewBinding implements Unbinder {
  private CUDFragment target;

  private View view7f0900bb;

  @UiThread
  public CUDFragment_ViewBinding(final CUDFragment target, View source) {
    this.target = target;

    View view;
    target.imeAutomobila = Utils.findRequiredViewAsType(source, R.id.imeAutomobila, "field 'imeAutomobila'", EditText.class);
    target.vrsta_automobila = Utils.findRequiredViewAsType(source, R.id.vrsta_automobila, "field 'vrsta_automobila'", Spinner.class);
    target.godina = Utils.findRequiredViewAsType(source, R.id.godina, "field 'godina'", EditText.class);
    target.opis = Utils.findRequiredViewAsType(source, R.id.opis, "field 'opis'", EditText.class);
    target.slika = Utils.findRequiredViewAsType(source, R.id.slika, "field 'slika'", ImageView.class);
    target.noviAutomobil = Utils.findRequiredViewAsType(source, R.id.noviAutomobil, "field 'noviAutomobil'", Button.class);
    target.Slikaj = Utils.findRequiredViewAsType(source, R.id.Slikaj, "field 'Slikaj'", Button.class);
    target.promjenaAutomobila = Utils.findRequiredViewAsType(source, R.id.promjenaAutomobila, "field 'promjenaAutomobila'", Button.class);
    target.obrisiAutomobil = Utils.findRequiredViewAsType(source, R.id.obrisiAutomobil, "field 'obrisiAutomobil'", Button.class);
    view = Utils.findRequiredView(source, R.id.natrag, "method 'nazad'");
    view7f0900bb = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.nazad();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    CUDFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.imeAutomobila = null;
    target.vrsta_automobila = null;
    target.godina = null;
    target.opis = null;
    target.slika = null;
    target.noviAutomobil = null;
    target.Slikaj = null;
    target.promjenaAutomobila = null;
    target.obrisiAutomobil = null;

    view7f0900bb.setOnClickListener(null);
    view7f0900bb = null;
  }
}
