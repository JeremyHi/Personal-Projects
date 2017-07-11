import subprocess as sp
import re
import os

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


# {'BackupPhase':Copying,
# 'ClientID':"com.apple.backupd",
# 'DateOfStateChange':"2017-05-30174211+0000",
# 'DestinationID':"6716D810-8576-4DF2-A64A-3C1211ADF886",
# 'DestinationMountPoint':"/Volumes/Jeremy'sTimeMachine",
# 'Percent':"0.04055064931961466",
# 'Progress':
#   {
#     'TimeRemaining':728,
#     '"_raw_totalBytes"':1027421373,
#     'bytes':46291782,
#     'files':2834,
#     'totalBytes':1130163510,
#     'totalFiles':33293,
#   },
# 'Running':1,
# 'Stopping':0,
# '"_raw_Percent"':"0.04505627702179406"}