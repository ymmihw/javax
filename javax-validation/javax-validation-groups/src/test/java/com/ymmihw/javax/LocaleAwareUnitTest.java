package com.ymmihw.javax;

import java.util.Locale;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public abstract class LocaleAwareUnitTest {
  private static Locale previousDefault;

  @BeforeAll
  public static void setupLocale() {
    previousDefault = Locale.getDefault();

    Locale.setDefault(Locale.US);
  }

  @AfterAll
  public static void resetLocale() {
    Locale.setDefault(previousDefault);
  }

}
