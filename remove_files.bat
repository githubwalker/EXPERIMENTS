rem git filter-branch --force --index-filter "git rm -r --cached --ignore-unmatch .metadata" --prune-empty --tag-name-filter cat -- --all

rem git filter-branch --force --index-filter "git rm -r --cached --ignore-unmatch FILES" --prune-empty --tag-name-filter cat -- --all