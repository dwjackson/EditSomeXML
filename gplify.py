from __future__ import print_function
import re
import os
import sys

NOTICE_FILE_NAME = 'gpl_notice.txt'
SRC_DIR = 'src'
JAVA_EXT = '.java'

def render_template(tmpl, data):
    template_regex = '\{\{([^\{\}]+)\}\}'
    r = re.compile(template_regex)
    text = r.sub(lambda m: data[m.group(1)], tmpl)
    return text

def commentize(text):
    comment = '/*\n'
    for line in text.split('\n'):
        comment += ' * ' + line + '\n'
    comment += ' */\n\n'
    return comment

def add_notice_to_files(dirname, notice, write_changes=False):
    os.chdir(dirname)
    for dirent in os.listdir(os.getcwd()):
        if os.path.isdir(dirent):
            add_notice_to_files(dirent, notice, write_changes)
        elif JAVA_EXT in dirent:
            print("[DEBUG] Directory = " + dirname)
            print(os.listdir(os.getcwd()))
            content = None
            with open(dirent, 'r') as f:
                content = f.read()
            if write_changes:
                os.rename(dirent, dirent + '.old')
            content_modified = notice + content
            print("File Name: " + dirent)
            print(content_modified)
            if write_changes:
                with open(dirent, 'w') as f:
                    f.write(content_modified)
    os.chdir('..')

def main(args):
    if len(args) == 2 and args[1] == 'write':
        write_changes = True
    else:
        write_changes = False
    notice_template = None
    with open(NOTICE_FILE_NAME, 'r') as f:
        notice_template = f.read()
    data = {'year': '2014', 'author': 'David Jackson'}
    data['description'] = 'EditSomeXML is a graphical XML editor'
    notice_text = render_template(notice_template, data)
    notice_comment = commentize(notice_text)
    add_notice_to_files(SRC_DIR, notice_comment, write_changes=write_changes)

if __name__ == '__main__':
    main(sys.argv)
