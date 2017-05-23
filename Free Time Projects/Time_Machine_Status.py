import subprocess as sp
import re

def tmStatus():
	rep = {" ": "", ":": "", "=": ":", "\n": "", "{": "{\'"}
	rep = dict((re.escape(k), v) for k, v in rep.items())
	pattern = re.compile("|".join(rep.keys()))
  
	(out, err) = (sp.Popen(["tmutil status"], stdout=sp.PIPE, shell=True)).communicate()
	out = pattern.sub(lambda m: rep[re.escape(m.group(0))], out.decode("utf-8"))
	out = ((out.replace(";", ",\'", out.count(';') - 1)).replace(";", "")).replace(":","\':")
  
	result = eval(out[out.index('{'):])
	return result

tmStatus()
