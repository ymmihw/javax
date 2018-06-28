package com.ymmihw.javax.validation.extractor;

import javax.validation.valueextraction.ExtractedValue;
import javax.validation.valueextraction.UnwrapByDefault;
import javax.validation.valueextraction.ValueExtractor;
import com.ymmihw.javax.validation.Profile;

@UnwrapByDefault
public class ProfileValueExtractor
    implements ValueExtractor<@ExtractedValue(type = String.class) Profile> {

  @Override
  public void extractValues(Profile originalValue, ValueExtractor.ValueReceiver receiver) {
    receiver.value(null, originalValue.getCompanyName());
  }

}