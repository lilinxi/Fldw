# cat.f

```shell script
stdin | stdout
# input: hello
# output: hello
```

---

# hello_world.f

```shell script
["Hello", "World"] | stdout
# output: Hello World
```

---

# match.f

```shell script
["Hello", "World", "!", "!"] | [a1, a2, a3] | [b1, b2, b3, b4] | stdout
# output: Hello World ! nil
```

```shell script
(["Hello", "World", "!", "!"] | [a1, a2, a3] ) | stdout
# output: True
```

---

# cast.f

```shell script
["Hello", "World"] | [a1, a2] | stdout
a1 | stdout
# output: Hello World\nH e l l o
```

---

# if.f

```shell script
seq(0, 100, 1) | x
if ( x % 2 == 0 ) { x | stdout }
else if ( x % 3 == 0 ) { x/3 | stdout }
else { x | nil }
```

---

# for.f

```shell script
for ( x=0; x<10; x++ ) {
	x | stdout
}
```

```shell script
[1, 1] | [f1, f2]
for ( x=0; x<10; x++ ) {
	[f2, f1+f2] | [f1, f2]
	f2 | stdout
}
```

---

# function.f

```shell script
function seq(begin, end, step) {
	for(x=begin; x<end; x+=step) {
		x | out
	}
}
```

---

# quicksort.f

```shell script
function sort() {
	if ( len(in) == 0 ) { }
	else { 
		in | [head;tail]
		tail | x
		if ( x < head ) {
			x | a
		} else {
			x | b
		}
 		[a|sort(), head, b|sort()] | out
	}
 }

[5, 6, 3, 2, 7, 8] | sort() | stdout
```

# map 和 filter（略）

