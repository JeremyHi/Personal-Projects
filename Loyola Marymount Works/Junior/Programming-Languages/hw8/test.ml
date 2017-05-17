let rec duplist(l1,l2) =
	match l1,l2 with
	| [], [] -> true
	| x::tl, x2::y2::tl2 -> x=x2 && x=y2
	| _ -> false