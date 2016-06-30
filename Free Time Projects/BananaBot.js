var Twit = require('twit')

var T = new Twit({
    consumer_key:         process.env.p4rUO7xc9YrxmL6sGPf2qPP0F
  , consumer_secret:      process.env.eCSYCtXZnG4v6yk1vArTCmcBHxl7IDBT9xUeCrRtq8gtQLObjF
  , access_token:         process.env.881011873wcXFEh88jQdw4mC8h6x1ib2hxnBFOMGzliTu3J49
  , access_token_secret:  process.env.DoifwfiCr9ZqJsZI2R7ha8RQfz2DLrT0JWZkMnmVDNVCR
})


// tweet something funny
T.post('statuses/update', {status: 'Just testing a bot'},  function(error, tweet, response){
  if(error) throw error;
  console.log(tweet);  // Tweet body. 
  console.log(response);  // Raw response object. 
});