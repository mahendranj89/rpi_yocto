SUMMARY = "Server module for error logging"
DESCRIPTION = "A UDP server that receives messages from the client and forwards them to the display module."
LICENSE = "CLOSED"
SRC_URI = "file://server-module.cpp"


DEPENDS = "virtual/libc"

S = "${WORKDIR}"

# Adding LDFLAGS globally to ensure linker options are applied
CXXFLAGS += "-Wl,--hash-style=gnu"

do_compile() {
    ${CXX} ${S}/server-module.cpp -o ${S}/server-module ${CXXFLAGS}
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/server-module ${D}${bindir}
}


FILES:${PN} = "${bindir}/server-module"

