SUMMARY = "Smart card library and applications"
DESCRIPTION = "OpenSC is a tool for accessing smart card devices. Basic\
functionality (e.g. SELECT FILE, READ BINARY) should work on any ISO\
7816-4 compatible smart card. Encryption and decryption using private\
keys on the smart card is possible with PKCS\
such as the FINEID (Finnish Electronic IDentity) card. Swedish Posten\
eID cards have also been confirmed to work."

HOMEPAGE = "https://github.com/OpenSC/OpenSC/wiki"
SECTION = "System Environment/Libraries"
LICENSE = "LGPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=cb8aedd3bced19bd8026d96a8b6876d7"

#v0.21.0
SRCREV = "0a4b772d6fdab9bfaaa3123775a48a7cb6c5e7c6"
SRC_URI = "git://github.com/OpenSC/OpenSC;branch=stable-0.25;protocol=https \
           file://0001-PR-Fixes-for-uninitialized-memory-issues.patch \
           file://CVE-2024-8443-0001.patch \
           file://CVE-2024-8443-0002.patch \
         "
DEPENDS = "virtual/libiconv openssl"

S = "${WORKDIR}/git"
inherit autotools pkgconfig bash-completion

EXTRA_OECONF = " \
    --disable-static \
    --disable-ctapi \
    --disable-doc \
    --disable-strict \
"
EXTRA_OEMAKE = "DESTDIR=${D}"

PACKAGECONFIG ??= "pcsc"

PACKAGECONFIG[openct] = "--enable-openct,--disable-openct,openct"
PACKAGECONFIG[pcsc] = "--enable-pcsc,--disable-pcsc,pcsc-lite,pcsc-lite pcsc-lite-lib"

RDEPENDS:${PN} = "readline"

FILES:${PN} += "\
    ${libdir}/opensc-pkcs11.so \
    ${libdir}/onepin-opensc-pkcs11.so \
    ${libdir}/pkcs11-spy.so \
"
FILES:${PN}-dev += "\
    ${libdir}/onepin-opensc-pkcs11.so \
    ${libdir}/pkcs11/opensc-pkcs11.so \
    ${libdir}/pkcs11/onepin-opensc-pkcs11.so \
    ${libdir}/pkcs11/pkcs11-spy.so \
"

BBCLASSEXTEND = "native"
