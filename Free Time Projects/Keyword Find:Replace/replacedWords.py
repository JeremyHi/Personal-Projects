# this is a comment

import re

keywords = ['get', '/shop/all/', 'category', 'indexOf', 'href', 'location', 'keyword', '', 'toLowerCase', 'color', '.inner-article > h1', 'size', '.inner-article', 'innerHTML', '.inner-article > h1 > a', 'getAttribute', 'trim', 'textContent', '.inner-article > p > a', 'not found', 'log', 'I am undefined', '.inner-article > a', 'done', 'GET', ':', 'split', 'text', 'title', 'filter', '-', '---', 'ajax', 'http://www.supremenewyork.com/shop/all/', '/shop/all', ':visible', 'is', '.in-cart', '#add-remove-buttons', 'selectedIndex', 'prop', '#size', 'each', '#size option', 'click', '[name="commit"]', 'ischeckout', 'shop/all', 'https://www.supremenewyork.com/checkout', 'sendMessage', 'extension']
s = str("""varkeywords=['get','/shop/all/','category','indexOf','href','location','keyword','','toLowerCase','color','.inner-article>h1','size','.inner-article','innerHTML','.inner-article>h1>a','getAttribute','trim','textContent','.inner-article>p>a','notfound','log','Iamundefined','.inner-article>a','done','GET',':','split','text','title','filter','-','---','ajax','http://www.supremenewyork.com/shop/all/','/shop/all',':visible','is','.in-cart','#add-remove-buttons','selectedIndex','prop','#size','each','#sizeoption','click','[name="commit"]','ischeckout','shop/all','https://www.supremenewyork.com/checkout','sendMessage','extension']$(function(){chrome['extension']['sendMessage']({method:'get'},function(_0x16efx1){var_0x16efx2=false;var_0x16efx3=setInterval(function(){if(window['location']['href']['indexOf']('/shop/all/'+_0x16efx1['category'])!=-1&&_0x16efx2===false){if(_0x16efx1['keyword']!=''&&_0x16efx1['keyword']!=undefined){var_0x16efx4=_0x16efx1['keyword']['toLowerCase']();var_0x16efx5=_0x16efx1['color']['toLowerCase']();if($('.inner-article>h1')[0]!=undefined){for(var_0x16efx6=0;_0x16efx6<$('inner-article')['size']();_0x16efx6++){var_0x16efx7=$('.inner-article>h1>a')[_0x16efx6]['innerHTML']['toLowerCase']();var_0x16efx8=$('.inner-article>h1>a')[_0x16efx6]['getAttribute']('href');var_0x16efx9=$(keywords[18])[_0x16efx6][keywords[17]][keywords[16]]()['toLowerCase']();if(_0x16efx7['indexOf'(_0x16efx4)!=-1&&(_0x16efx9==_0x16efx5)&&_0x16efx2===false){_0x16efx2=true;window['location']['href']=_0x16efx8;break}else{console[keywords[20]](keywords[19])}}}else{console[keywords[20]](keywords[21]);for(var_0x16efx6=0;_0x16efx6<$('inner-article')['size']();_0x16efx6++){var_0x16efx8=$(keywords[22])[_0x16efx6]['getAttribute']('href');var_0x16efx7=null;var_0x16efx9=null;$[keywords[32]]({type:keywords[24],async:false,url:_0x16efx8,success:function(_0x16efxa){var_0x16efxb=$(_0x16efxa)[keywords[29]](keywords[28])[keywords[27]]()[keywords[26]](keywords[25]);var_0x16efxc=_0x16efxb[1][keywords[26]](keywords[30]);_0x16efx7=_0x16efxc[0][keywords[16]]()['toLowerCase']();_0x16efx9=_0x16efxc[1][keywords[16]]()['toLowerCase']();console[keywords[20]](_0x16efx7+keywords[31]+_0x16efx9)},error:function(_0x16efxd){}})[keywords[23]](function(_0x16efx1){});if(_0x16efx7['indexOf'(_0x16efx4)!=-1&&(_0x16efx9==_0x16efx5)&&_0x16efx2===false){_0x16efx2=true;window['location']['href']=_0x16efx8;break}else{console[keywords[20]](keywords[19])}}};if(_0x16efx2===false){clearInterval(_0x16efx3);window['location']['href']=keywords[33]+_0x16efx1['category']}}}});var_0x16efxe=setInterval(function(){if(window['location']['href']['indexOf'(keywords[34])==-1){if(!$(keywords[37])[keywords[36]](keywords[35])&&$(keywords[38])[keywords[36]](keywords[35])){$(keywords[43])[keywords[42]](function(_0x16efx6){if($(this)[keywords[27]]()==_0x16efx1['size']){$(keywords[41])[keywords[40]](keywords[39],_0x16efx6)}});$(keywords[45])[keywords[44]]()}}},100);if(_0x16efx1[keywords[46]]==1){var_0x16efxf=setInterval(function(){if($(keywords[37])[keywords[36]](keywords[35])&&window['location']['href']['indexOf'(keywords[47])==-1){window['location']=keywords[48];clearInterval(_0x16efxf)}},100)}})})""")

indexBank = [m.start() for m in re.finditer('keywords', s)]

wordBank = []
for x in indexBank:
	wordBank.append(s[x:(x+12)])

wordBank = sorted(wordBank[2::])
indexBank = sorted(indexBank[1::])


for y in wordBank:
	s = s.replace(y, keywords[int(y[9:11])])


