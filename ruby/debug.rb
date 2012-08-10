module Debug
  @@debug = false
 
   def Debug.debug
     return @@debug
   end 
   
   def Debug.debug=(debug)
     @@debug = debug 
   end
 
end