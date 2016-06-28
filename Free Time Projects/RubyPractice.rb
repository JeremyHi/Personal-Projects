def strip_vowels(word)
	word = word.gsub(/[AEIOU]/i, '')
	puts word
end

yup = "SEeAcIrOeUtE AMIeOsUsOaEgIeO"
strip_vowels(yup)