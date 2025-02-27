SUMMARY  = "pandas library for high-performance data analysis tools"
DESCRIPTION = "pandas is an open source, BSD-licensed library providing \
high-performance, easy-to-use data structures and data analysis tools for \
the Python programming language."
HOMEPAGE = "http://pandas.pydata.org/"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=cb819092901ddb13a7d0a4f5e05f098a"

SRC_URI[sha256sum] = "c02f372a88e0d17f36d3093a644c73cfc1788e876a7c4bcb4020a77512e2043c"

inherit pypi setuptools3

DEPENDS += " \
    python3-cython-native \
    python3-numpy-native \
    python3-versioneer-native \
"

PACKAGESPLITFUNCS =+ "fix_cythonized_sources"

fix_cythonized_sources() {
	for f in `grep -l -r -e '\/* Generated by Cython.*/$' ${PKGD}${TARGET_DBGSRC_DIR}`; do
		if [ -e $f ]; then
			sed -i -e 's#${RECIPE_SYSROOT_NATIVE}##g' $f
		fi
	done
}

CFLAGS:append:toolchain-clang = " -Wno-error=deprecated-declarations"

RDEPENDS:${PN} += " \
    python3-json \
    python3-numpy \
    python3-dateutil \
    python3-dateutil-zoneinfo \
    python3-pytz \
    python3-profile \
"
