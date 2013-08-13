Java Downsample Implementation
==============================

This is an implementation of down-sampling as used by the flot charts 
plugin. This is not really my work, I just translated the code from 
Javascript and read the thesis.

[Implementation in Javascript](https://github.com/sveinn-steinarsson/flot-downsample)

# Interesting Features

My data often has gaps (in time) and I need to cope with that, I also 
want to cache the down-sampled data and so I have made use of a single 
interval method. It is likely that this method, in the current 
implementation will cause rounding errors, this is evident in the 
example  data provided with this package. To cache the data provided 
by the routine I expect a single interval would be desirable.

# External Libraries

I have made use of JFreeChart in my tests, this is just so you can see 
the actual data and the downsampled data.
