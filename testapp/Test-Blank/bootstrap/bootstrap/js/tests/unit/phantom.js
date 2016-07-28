// Simple phantom.js integration script
// Adapted from Modernizr

function waitFor(testFx, onReady, timeOutMillis) {
  var maxtimeOutMillis = timeOutMillis ? timeOutMillis :  5001 //< Default Max Timout is 5s
    , start = new Date().getTime()
    , condition = false
    , interval = setInterval(function () {
        if ((new Date().getTime() - start < maxtimeOutMillis) && !condition) {
          // If not time-out yet and condition not yet fulfilled
          condition = (typeof(testFx) === "string" ? eval(testFx) : testFx()) //< defensive code
        } else {
          if (!condition) {
            // If condition still not fulfilled (timeout but condition is 'false')
            console.log("'waitFor()' timeout")
            phantom.exit(1)
          } else {
            // Condition fulfilled (timeout and/or condition is 'true')
            typeof(onReady) === "string" ? eval(onReady) : onReady() //< Do what it's supposed to do once the condition is fulfilled
            clearInterval(interval) //< Stop this interval
          }
        }
    }, 100) //< repeat check every 100ms
}


if (phantom.args.length === 0 || phantom.args.length > 2) {
  console.log('Usage: phantom.js URL')
  phantom.exit()
}

var page = new WebPage()

// Route "console.log()" calls from within the Page context to the main Phantom context (i.e. current "this")
page.onConsoleMessage = function(msg) {
  console.log(msg)
};

page.open(phantom.args[0], function(status){
  if (status !== "success") {
    console.log("Unable to access network")
    phantom.exit()
  } else {
    waitFor(function(){
      return page.evaluate(function(){
        var el = document.getElementById('qunit-testresult')
        if (el && el.innerText.match('completed')) {
          return true
        }
        return false
      })
    }, function(){
      var failedNum = page.evaluate(function(){
        var el = document.getElementById('qunit-testresult')
        try {
          return el.getElementsByClassName('failed')[0].innerHTML
        } catch (e) { }
        return 10000
      });
      phantom.exit((parseInt(failedNum, 10) > 0) ? 1 : 0)
    })
  }
})


// Logging setup for phantom integration
// adapted from Modernizr

QUnit.begin = function () {
  console.log("Starting test suite")
  console.log("================================================\n")
}

QUnit.moduleDone = function (opts) {
  if (opts.failed === 0) {
    console.log("\u2714 All tests passed in '" + opts.name + "' module")
  } else {
    console.log("\u2716 " + opts.failed + " tests failed in '" + opts.name + "' module")
  }
}

QUnit.done = function (opts) {
  console.log("\n================================================")
  console.log("Tests completed in " + opts.runtime + " milliseconds")
  console.log(opts.passed + " tests of " + opts.total + " passed, " + opts.failed + " failed.")
}