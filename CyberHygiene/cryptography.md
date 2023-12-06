# Encrypting

				          key
				           |
				           |
			        |---------|
plaintext---->|    E    |---->ciphertext
			        |---------|

## Cryptology

Cryptography + Cryptanalysis

## Symmetric encryption

* k: key
* m: message
* E: encryption function
* D: decryption function

`m' = E(k,m)`

`m = D(k,m)`

Advantage : fast

## Asymmetric encryption

* puk: public key
* prk: private key

`m' = E(puk,m)`

`m = D(prk,m)`

Advantage : everyone can know the public key and use it to encrypt

## Symmetric algorithms

Today the NSA is supposed to not be able to bruteforce a 128-bit key.

3DES -> ~ 112 bits (a bit risky)

USE AES ! There is (almost) no reason to not use it.

* |AES key| = 128 or 192 or 256 bits
* |AES block| = 128 bits
---------
* |DES key| = 56 bits
* |DES block| = 64 bits

## Asymmetric algorithms

* intractable problem = no clever algorithm to solve it
* Standard key : 2048 bits
* RSA is good

### RSA:

`m' = m^e mod n`
`m = m'^d mod n`

Where:
* n = pq with p,q prime numbers (1024 bits each)
* ed = 1 mod (p-1)(q-1) (because phi(n) = (p-1)(q-1))

public-key: `(e,n)`

private-key: `(d,n)`

# Signature scheme

Relies on public-key cryptography

* Sender signs the message with the private key
* Receiver checks authenticity using the public key

# Hash function

"One-way" function : easy to do one way, not the other

Slide 285 for properties

* Infeasible to revert
* Infeasible to find a preimage from another
* Infeasible to find a collision
* Images should resemble random values

Do not use SHA-1 for the future

* |RSA modulus| >= 2048 bits
* |MD-5 output| = 128 bits
* |SHA-1 output| = 160 bits

SHA-2 is kinda similar to SHA-1 (which was broken) so SHA-3 exists just in case.

# MAC

keyed-hash function = hash function with a key !

# Sane defaults for common algorithms
Slide 291
